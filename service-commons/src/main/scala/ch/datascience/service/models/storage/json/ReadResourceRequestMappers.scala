package ch.datascience.service.models.storage.json

import ch.datascience.service.models.resource.json.RequestTypeMappers
import ch.datascience.service.models.storage.ReadResourceRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ReadResourceRequestMappers {

  def ReadResourceRequestFormat: OFormat[ReadResourceRequest] = RequestTypeMappers.format("read_file")(
    (JsPath \ "resource_id").format[Long].inmap(ReadResourceRequest.apply, unlift(ReadResourceRequest.unapply))
  )

}
