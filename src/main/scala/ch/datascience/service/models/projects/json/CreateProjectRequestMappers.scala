package ch.datascience.service.models.projects.json

import ch.datascience.service.models.projects.CreateProjectRequest
import ch.datascience.service.models.resource.json.RequestTypeMappers
import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, OFormat }

object CreateProjectRequestMappers {

  def CreateProjectRequestFormat: OFormat[CreateProjectRequest] = RequestTypeMappers.format( "create_project" )(
    ( JsPath \ "name" ).format[String].inmap( CreateProjectRequest.apply, unlift( CreateProjectRequest.unapply ) )
  )

}
