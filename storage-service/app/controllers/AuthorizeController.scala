package controllers

import java.util.UUID
import javax.inject.{Inject, Singleton}

import ch.datascience.graph.Constants
import ch.datascience.graph.elements.mutation.create.CreateVertexOperation
import ch.datascience.graph.elements.mutation.{GraphMutationClient, Mutation}
import ch.datascience.graph.elements.new_.build.NewVertexBuilder
import ch.datascience.graph.elements.persisted.PersistedVertex
import ch.datascience.graph.elements.persisted.json.PersistedVertexFormat
import ch.datascience.graph.naming.NamespaceAndName
import ch.datascience.graph.values.{StringValue, UuidValue}
import models.{CreateBucketRequest, ReadResourceRequest, WriteResourceRequest}
import models.json._
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.pac4j.play.store.PlaySessionStore
import persistence.graph.{GraphExecutionContextProvider, JanusGraphTraversalSourceProvider}
import persistence.reader.VertexReader
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import clients.ResourcesManagerClient

import scala.collection.JavaConversions._
import scala.concurrent.Future

/**
  * Created by jeberle on 25.04.17.
  */
@Singleton
class AuthorizeController @Inject()(config: play.api.Configuration,
                                    implicit val playSessionStore: PlaySessionStore,
                                    implicit val wsclient: WSClient
                                    ) extends Controller with JsonComponent with RequestHelper{

  lazy val host: String = config
    .getString("resources.manager.service.host")
    .getOrElse("http://localhost:9000/api/resources/")

  def objectRead = Action.async(bodyParseJson[ReadResourceRequest](readResourceRequestReads)) { implicit request =>
    Future {
      implicit val token: String = request.headers.get("Authorization").getOrElse("")
      val rrr = request.body
      val rmc = new ResourcesManagerClient(host)
      rmc.authorize(readResourceRequestWrites, rrr)

      // TODO Check permission and forward token

      Ok
    }
  }

  def objectWrite = Action.async(bodyParseJson[WriteResourceRequest](writeResourceRequestReads)) { implicit request =>
    Future {
      implicit val token: String = request.headers.get("Authorization").getOrElse("")
      val wrr = request.body
      val rmc = new ResourcesManagerClient(host)
      rmc.authorize(writeResourceRequestWrites, wrr)

      // TODO Check permission and forward token

      Ok
    }
  }

  def bucketCreate = Action.async(bodyParseJson[CreateBucketRequest](createBucketRequestReads)) { implicit request =>
    Future{
      implicit val token: String = request.headers.get("Authorization").getOrElse("")
      val cbr = request.body
      val rmc = new ResourcesManagerClient(host)
      rmc.authorize(createBucketRequestWrites, cbr)

  // TODO Check permission and forward token

  Ok
    }

  }
}