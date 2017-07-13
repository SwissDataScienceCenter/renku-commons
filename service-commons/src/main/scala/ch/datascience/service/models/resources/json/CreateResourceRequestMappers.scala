package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.{CreateFileRequest, CreateResourceRequest}
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 13/07/17.
  */
object CreateResourceRequestMappers {

  def CreateResourceRequestFormat: OFormat[CreateResourceRequest] = OFormat(CreateResourceRequestReads, CreateResourceRequestWrites)

  def CreateResourceRequestReads: Reads[CreateResourceRequest] = {
    (JsPath \ "resource_type").read[String].flatMap {
      case "file" => JsPath.read[CreateFileRequest].map(req => req: CreateResourceRequest)
    }
  }

  def CreateResourceRequestWrites: OWrites[CreateResourceRequest] = {
    val w1: OWrites[(String, JsObject)] = (
      (JsPath \ "resource_type").write[String] and
      JsPath.write[JsObject]
    )(unlift(Tuple2.unapply[String, JsObject]))

    w1.contramap { req: CreateResourceRequest =>
      req match {
        case r: CreateFileRequest => ("file", CreateFileRequestFormat.writes(r))
      }
    }
  }

  private[this] implicit lazy val CreateFileRequestFormat: OFormat[CreateFileRequest] = CreateFileRequestMappers.CreateFileRequestFormat

}
