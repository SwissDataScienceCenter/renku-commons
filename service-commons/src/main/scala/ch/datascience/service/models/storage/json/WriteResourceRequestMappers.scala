package ch.datascience.service.models.storage.json

import ch.datascience.service.models.resource.json.RequestTypeMappers
import ch.datascience.service.models.storage.WriteResourceRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object WriteResourceRequestMappers {

  def WriteResourceRequestFormat: OFormat[WriteResourceRequest] = RequestTypeMappers.format("write_file")(
    (JsPath \ "resource_id").format[Long].inmap(WriteResourceRequest.apply, unlift(WriteResourceRequest.unapply))
  )

}
