package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ResourceAccessRequest, ResourceScope}
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ResourceAccessRequestMappers {

  def ResourceAccessRequestFormat: OFormat[ResourceAccessRequest] = (
    (JsPath \ "resource_id").format[Long] and
      (JsPath \ "scopes").format[Seq[ResourceScope]]
  )({ (i, s) => ResourceAccessRequest(i, s: _*) }, { req => (req.resourceId, req.scopes.toSeq) })

}
