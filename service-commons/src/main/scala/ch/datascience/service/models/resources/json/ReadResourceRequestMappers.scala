package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.ReadResourceRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ReadResourceRequestMappers {

  def readResourceRequestReads: Reads[ReadResourceRequest] = (
    (JsPath \ "app_id").readNullable[Long] and
      (JsPath \ "resource_id").read[Long]
    )(ReadResourceRequest.apply _)

  def readResourceRequestWrites: Writes[ReadResourceRequest] = (
    (JsPath \ "app_id").writeNullable[Long] and
      (JsPath \ "resource_id").write[Long]
    ) { rr => (rr.appId, rr.resourceId) }
}
