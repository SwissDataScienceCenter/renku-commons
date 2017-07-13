package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.ReadResourceRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ReadResourceRequestMappers {

  def ReadResourceRequestFormat: OFormat[ReadResourceRequest] = {
    val reads = ((JsPath \ "resource_id").read[Long] and JsPath.read[JsObject]){ (id, _) => ReadResourceRequest(id) }
    val writes = (JsPath \ "resource_id").write[Long].contramap(unlift(ReadResourceRequest.unapply))

    JsPath.format[ReadResourceRequest](reads)(writes)
  }

}
