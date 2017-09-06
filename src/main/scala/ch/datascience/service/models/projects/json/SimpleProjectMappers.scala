package ch.datascience.service.models.projects.json

import ch.datascience.service.models.projects.SimpleProject
import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, OFormat }

object SimpleProjectMappers {

  def SimpleProjectFormat: OFormat[SimpleProject] = (
    ( JsPath \ "identifier" ).format[String] and
    ( JsPath \ "name" ).format[String] and
    ( JsPath \ "labels" ).formatNullable[Seq[String]]
  )( read, write )

  private[this] def read( id: String, name: String, labels: Option[Seq[String]] ): SimpleProject = {
    SimpleProject( id.toLong, name, labels.map( _.toSet ).getOrElse( Set.empty ) )
  }

  private[this] def write( project: SimpleProject ): ( String, String, Option[Seq[String]] ) = {
    val labels = if ( project.labels.isEmpty ) None else Some( project.labels.toSeq )
    ( project.id.toString, project.name, labels )
  }

}
