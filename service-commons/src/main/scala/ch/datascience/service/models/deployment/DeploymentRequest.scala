package ch.datascience.service.models.deployment

import ch.datascience.service.models.resource.{ScopeQualifier, SingleScopeAccessRequest}
import ch.datascience.service.models.storage.CreateBucketRequest
import play.api.libs.json.JsObject

/**
  * Created by johann on 10/07/17.
  */
case class DeploymentRequest(deploymentType: String, parentId: Option[Long], options: Option[JsObject]) extends SingleScopeAccessRequest.ToSingleScopeAccessRequest {

  def toAccessRequest(extraClaims: Option[JsObject]): SingleScopeAccessRequest = {
    SingleScopeAccessRequest(permissionHolderId = parentId, DeploymentRequest.scope, extraClaims)
  }

}

object DeploymentRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.DeploymentCreate
}
