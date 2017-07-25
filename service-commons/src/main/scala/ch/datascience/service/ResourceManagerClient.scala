package ch.datascience.service

import javax.inject.Inject

import ch.datascience.service.models.resource.AccessGrant
import ch.datascience.service.models.resource.json.AccessGrantFormat
import play.api.Configuration
import play.api.libs.json._
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class ResourceManagerClient @Inject()(configuration: Configuration)(implicit context: ExecutionContext, ws: WSClient) {

  lazy val rmConfig: Configuration = configuration.getConfig("resource-manager.service").get

  lazy val host: String = rmConfig.getString("host").get

  def authorize[T](writer: Writes[T], request: T, token: String): Future[Option[AccessGrant]] = {
    val wsRequest: WSRequest = ws.url(host + "/authorize")
      .withHeaders("Accept" -> "application/json", "Authorization" -> token)
      .withRequestTimeout(10000.millis)
    wsRequest.post(Json.toJson(request)(writer)).map {
      response =>
        response.json.validate(AccessGrantFormat).asOpt
    }
  }

}
