package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.WriteResourceRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object WriteResourceRequestMappers {

  def WriteResourceRequestFormat: OFormat[WriteResourceRequest] = {
    val reads = ((JsPath \ "resource_id").read[Long] and JsPath.read[JsObject]){ (id, _) => WriteResourceRequest(id) }
    val writes = (JsPath \ "resource_id").write[Long].contramap(unlift(WriteResourceRequest.unapply))

    JsPath.format[WriteResourceRequest](reads)(writes)
  }

}
