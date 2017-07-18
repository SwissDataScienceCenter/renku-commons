package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ResourceAccessRequest, ScopeQualifier}
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ResourceAccessRequestMappers {

  def ResourceAccessRequestFormat: OFormat[ResourceAccessRequest] = (
    (JsPath \ "resource_id").format[Long] and
      (JsPath \ "scope").format[Seq[ScopeQualifier]] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
  )({ (i, s, e) => ResourceAccessRequest(i, s.toSet, e) }, { req => (req.resourceId, req.scope.toSeq, req.extraClaims) })

}
