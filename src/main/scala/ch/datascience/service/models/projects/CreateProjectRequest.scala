package ch.datascience.service.models.projects

import ch.datascience.service.models.resource.{ ScopeQualifier, SingleScopeAccessRequest }
import play.api.libs.json.JsObject

case class CreateProjectRequest( name: String ) extends SingleScopeAccessRequest.ToSingleScopeAccessRequest {

  def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest = {
    SingleScopeAccessRequest( permissionHolderId = None, CreateProjectRequest.scope, extraClaims )
  }

}

object CreateProjectRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.ProjectCreate
}
