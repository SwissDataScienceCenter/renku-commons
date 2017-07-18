package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ReadResourceRequest, ScopeQualifier}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ReadResourceRequestMappers {

  def ReadResourceRequestFormat: OFormat[ReadResourceRequest] = (
    (JsPath \ "scope").format[ScopeQualifier](Reads.filter[ScopeQualifier](ValidationError("wrong scope"))(_ == ReadResourceRequest.scope)) and
      (JsPath \ "resource_id").format[Long] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
    )(read, write)

  private[this] def read(
    scope: ScopeQualifier,
    resourceId: Long,
    extraClaims: Option[JsObject]
  ): ReadResourceRequest = ReadResourceRequest(resourceId, extraClaims)

  private[this] def write(req: ReadResourceRequest): (ScopeQualifier, Long, Option[JsObject]) = {
    (req.scope, req.resourceId, req.extraClaims)
  }

}
