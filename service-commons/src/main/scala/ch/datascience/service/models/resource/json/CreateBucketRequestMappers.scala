package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{CreateBucketRequest, ResourceScope}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object CreateBucketRequestMappers {

  def CreateBucketRequestFormat: OFormat[CreateBucketRequest] = (
    (JsPath \ "scope").format[ResourceScope](Reads.filter[ResourceScope](ValidationError("wring scope"))(_ == CreateBucketRequest.scope)) and
      (JsPath \ "name").format[String] and
      (JsPath \ "backend").format[String] and
      (JsPath \ "options").formatNullable[JsObject]
    ) (read, write)

  private[this] def read(
    scope: ResourceScope,
    name: String,
    backend: String,
    options: Option[JsObject]
  ): CreateBucketRequest = CreateBucketRequest(name, backend, options)

  private[this] def write(req: CreateBucketRequest): (ResourceScope, String, String, Option[JsObject]) = {
    (req.scope, req.name, req.backend, req.backendOptions)
  }

}
