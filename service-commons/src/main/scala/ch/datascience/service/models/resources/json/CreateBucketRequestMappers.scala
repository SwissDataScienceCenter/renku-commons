package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.CreateBucketRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object CreateBucketRequestMappers {

  def createBucketRequestReads: Reads[CreateBucketRequest] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "backend").read[String]
    )(CreateBucketRequest.apply _)

  def createBucketRequestWrites: Writes[CreateBucketRequest] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "backend").write[String]
    ) { cb => (cb.name, cb.backend) }
}
