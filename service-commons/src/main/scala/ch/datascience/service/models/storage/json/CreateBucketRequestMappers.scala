package ch.datascience.service.models.storage.json

import ch.datascience.service.models.resource.json.RequestTypeMappers
import ch.datascience.service.models.storage.CreateBucketRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._

object CreateBucketRequestMappers {

  def CreateBucketRequestFormat: OFormat[CreateBucketRequest] = RequestTypeMappers.format( "create_bucket" )( (
    ( JsPath \ "name" ).format[String] and
    ( JsPath \ "backend" ).format[String] and
    ( JsPath \ "options" ).formatNullable[JsObject]
  )( CreateBucketRequest.apply, unlift( CreateBucketRequest.unapply ) ) )

}
