package ch.datascience.typesystem.model.table

import java.util.UUID

import ch.datascience.typesystem.model.row.GraphDomain
import ch.datascience.typesystem.{AsyncUnitSpec, DatabaseSetup, UnitSpec}
import org.scalatest.{BeforeAndAfterEach, Suite}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by johann on 13/04/17.
  */
class GraphDomainSpec extends AsyncUnitSpec with DatabaseSetup with BeforeAndAfterEach {

  import profile.api._

  behavior of "GraphDomains table"

  it should "be empty" in {
    val f = db.run(dal.graphDomains.result)
    f map { list => list shouldBe empty }
  }

  it should "allow to add a graph domain" in {
    val graphDomain = GraphDomain(UUID.randomUUID(), "foo")
    val f = db.run(dal.graphDomains.add(graphDomain))
    f map { count => count shouldBe 1 }
  }

  it should "allow to add a graph domain and get it back" in {
    val graphDomain = GraphDomain(UUID.randomUUID(), "foo")
    val insert: DBIO[Int] = dal.graphDomains.add(graphDomain)
    val select: DBIO[Option[GraphDomain]] = dal.graphDomains.findByNamespace("foo").result.headOption
    val f = db.run(insert andThen select)
    f map { opt => opt shouldBe Some(graphDomain) }
  }

  override protected def afterEach(): Unit = {
    try super.afterEach()
    finally {
      val dropAll: DBIO[Int] = dal.graphDomains.delete
      val run = db.run(dropAll)
      Await.result(run, Duration.Inf)
    }
  }

}
