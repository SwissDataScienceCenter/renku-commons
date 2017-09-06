package ch.datascience.service.models.projects

import play.api.libs.json.OFormat

package object json {

  implicit lazy val CreateProjectRequestFormat: OFormat[CreateProjectRequest] = CreateProjectRequestMappers.CreateProjectRequestFormat
  implicit lazy val SimpleProjectFormat: OFormat[SimpleProject] = SimpleProjectMappers.SimpleProjectFormat

}
