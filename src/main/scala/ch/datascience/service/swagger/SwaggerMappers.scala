package ch.datascience.service.swagger

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

object SwaggerMappers {

  def updateHostSchemesAndAuthorizationUrl( host: String, schemes: Seq[String], url: String ): Reads[JsObject] = {
    updateHost( host ) andThen updateSchemes( schemes ) andThen updateAuthorizationUrl( url )
  }

  def updateHost( host: String ): Reads[JsObject] = {
    (
      ( JsPath \ "host" ).json.prune and
      ( JsPath \ "host" ).json.put( JsString( host ) )
    ).reduce
  }

  def updateSchemes( schemes: Seq[String] ): Reads[JsObject] = {
    (
      ( JsPath \ "schemes" ).json.prune and
      ( JsPath \ "schemes" ).json.put( JsArray( schemes.map( JsString ) ) )
    ).reduce
  }

  def updateAuthorizationUrl( url: String ): Reads[JsObject] = {
    ( JsPath \ "securityDefinitions" \ "token_auth" ).json.update(
      JsPath.read[JsObject].map { o => o ++ Json.obj( "authorizationUrl" -> url ) }
    ).orElse( JsPath.read[JsObject] )
  }

}
