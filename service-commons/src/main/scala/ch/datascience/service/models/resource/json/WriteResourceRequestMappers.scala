package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ResourceScope, WriteResourceRequest}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object WriteResourceRequestMappers {

  def WriteResourceRequestFormat: OFormat[WriteResourceRequest] = (
    (JsPath \ "scope").format[ResourceScope](Reads.filter[ResourceScope](ValidationError("wring scope"))(_ == WriteResourceRequest.scope)) and
      (JsPath \ "resource_id").format[Long]
    )(read, write)

  private[this] def read(
    scope: ResourceScope,
    resourceId: Long
  ): WriteResourceRequest = WriteResourceRequest(resourceId)

  private[this] def write(req: WriteResourceRequest): (ResourceScope, Long) = {
    (req.scope, req.resourceId)
  }

}
