package ch.datascience.service.models.projects.json

import ch.datascience.service.models.projects.CreateProjectRequest
import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, OFormat }

object CreateProjectRequestMappers {

  def CreateProjectRequestFormat: OFormat[CreateProjectRequest] = (
    ( JsPath \ "name" ).format[String] and
    ( JsPath \ "labels" ).formatNullable[Seq[String]]
  )( read, write )

  private[this] def read( name: String, labels: Option[Seq[String]] ): CreateProjectRequest = {
    CreateProjectRequest( name, labels.map( _.toSet ).getOrElse( Set.empty ) )
  }

  private[this] def write( request: CreateProjectRequest ): ( String, Option[Seq[String]] ) = {
    val labels = if ( request.labels.isEmpty ) None else Some( request.labels.toSeq )
    ( request.name, labels )
  }

}
