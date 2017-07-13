package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.CreateFileRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 13/07/17.
  */
private[json] object CreateFileRequestMappers {

  def CreateFileRequestFormat: OFormat[CreateFileRequest] = (
    (JsPath \ "bucket_id").format[Long] and
      (JsPath \ "file_name").format[String]
    )(CreateFileRequest.apply, unlift(CreateFileRequest.unapply))

}
