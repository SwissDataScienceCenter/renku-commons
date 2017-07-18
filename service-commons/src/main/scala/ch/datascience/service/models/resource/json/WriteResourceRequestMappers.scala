package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ScopeQualifier, WriteResourceRequest}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object WriteResourceRequestMappers {

  def WriteResourceRequestFormat: OFormat[WriteResourceRequest] = (
    (JsPath \ "scope").format[ScopeQualifier](Reads.filter[ScopeQualifier](ValidationError("wrong scope"))(_ == WriteResourceRequest.scope)) and
      (JsPath \ "resource_id").format[Long] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
    )(read, write)

  private[this] def read(
    scope: ScopeQualifier,
    resourceId: Long,
    extraClaims: Option[JsObject]
  ): WriteResourceRequest = WriteResourceRequest(resourceId, extraClaims)

  private[this] def write(req: WriteResourceRequest): (ScopeQualifier, Long, Option[JsObject]) = {
    (req.scope, req.resourceId, req.extraClaims)
  }

}
