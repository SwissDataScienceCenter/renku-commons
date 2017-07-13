package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.CreateBucketRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object CreateBucketRequestMappers {

  def CreateBucketRequestFormat: OFormat[CreateBucketRequest] = (
    (JsPath \ "name").format[String] and
      (JsPath \ "backend").format[String]
    )(CreateBucketRequest.apply, unlift(CreateBucketRequest.unapply))

}
