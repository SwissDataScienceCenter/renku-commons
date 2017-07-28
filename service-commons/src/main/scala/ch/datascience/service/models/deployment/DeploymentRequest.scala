package ch.datascience.service.models.deployment

import ch.datascience.service.models.resource.{ ScopeQualifier, SingleScopeAccessRequest }
import ch.datascience.service.models.storage.CreateBucketRequest
import play.api.libs.json.JsObject

/**
 * Created by johann on 10/07/17.
 */
case class DeploymentRequest( deploymentType: String, options: Option[JsObject] ) extends SingleScopeAccessRequest.ToSingleScopeAccessRequest { self =>

  def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest = {
    SingleScopeAccessRequest( permissionHolderId = None, DeploymentRequest.scope, extraClaims )
  }

  def withParent( parentId: Long ): SingleScopeAccessRequest.ToSingleScopeAccessRequest = new SingleScopeAccessRequest.ToSingleScopeAccessRequest {
    def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest = {
      SingleScopeAccessRequest( permissionHolderId = Some( parentId ), DeploymentRequest.scope, extraClaims )
    }
  }

}

object DeploymentRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.DeploymentCreate
}
