package ch.datascience.graph.types.persistence.orchestration

import java.util.UUID

import ch.datascience.graph.naming.NamespaceAndName
import ch.datascience.graph.types.{Cardinality, DataType}
import ch.datascience.graph.types.persistence.model.{GraphDomain, PropertyKey}

import scala.concurrent.Future

/**
  * Created by johann on 04/04/17.
  */
trait PropertyKeyComponent {
  this: ExecutionComponent with DatabaseComponent with GraphComponent =>

  import profile.api._
  import dal._

  object propertyKeys {

    def all(): Future[Seq[PropertyKey]] = {
      db.run( dal.propertyKeys.mapped.result )
    }

    def findById(id: UUID): Future[Option[PropertyKey]] = {
      db.run( dal.propertyKeys.findById(id).result.headOption )
    }

    def findByNamespaceAndName(key: NamespaceAndName): Future[Option[PropertyKey]] = {
      findByNamespaceAndName(key.namespace, key.name)
    }

    def findByNamespaceAndName(namespace: String, name: String): Future[Option[PropertyKey]] = {
      db.run( dal.propertyKeys.findByNamespaceAndName(namespace, name).result.headOption )
    }

    def createPropertyKey(
      graphDomain: GraphDomain,
      name: String,
      dataType: DataType = DataType.String,
      cardinality: Cardinality = Cardinality.Single
    ): Future[PropertyKey] = {
      val fullname = s"${graphDomain.namespace}:$name"
      val propertyKey = PropertyKey(UUID.randomUUID(), graphDomain, name, dataType, cardinality)
      val insertPropertyKey = dal.propertyKeys add propertyKey
      val propagateChange = insertPropertyKey flatMap { _ =>
        val future = gdb.run(gal.propertyKeys.addPropertyKey(fullname, dataType, cardinality)).map(_ => propertyKey)
        DBIO.from(future)
      }
      db.run(propagateChange.transactionally)
    }

    def createPropertyKey(
      namespace: String,
      name: String,
      dataType: DataType,
      cardinality: Cardinality
    ): Future[PropertyKey] = {
      val selectGraphDomain = db.run( dal.graphDomains.findByNamespace(namespace).result.headOption.map(_.get) )
      selectGraphDomain flatMap { graphDomain => createPropertyKey(graphDomain, name, dataType, cardinality) }
    }

  }

}
