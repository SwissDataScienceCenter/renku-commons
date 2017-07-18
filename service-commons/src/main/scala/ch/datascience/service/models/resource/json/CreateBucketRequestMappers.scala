package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{CreateBucketRequest, ScopeQualifier}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object CreateBucketRequestMappers {

  def CreateBucketRequestFormat: OFormat[CreateBucketRequest] = (
    (JsPath \ "scope").format[ScopeQualifier](Reads.filter[ScopeQualifier](ValidationError("wring scope"))(_ == CreateBucketRequest.scope)) and
      (JsPath \ "name").format[String] and
      (JsPath \ "backend").format[String] and
      (JsPath \ "options").formatNullable[JsObject] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
    ) (read, write)

  private[this] def read(
    scope: ScopeQualifier,
    name: String,
    backend: String,
    options: Option[JsObject],
    extraClaims: Option[JsObject]
  ): CreateBucketRequest = CreateBucketRequest(name, backend, options, extraClaims)

  private[this] def write(req: CreateBucketRequest): (ScopeQualifier, String, String, Option[JsObject], Option[JsObject]) = {
    (req.scope, req.name, req.backend, req.backendOptions, req.extraClaims)
  }

}
