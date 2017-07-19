package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by johann on 14/07/17.
  */
class SingleScopeResourceAccessRequest(
  resourceId: AccessRequest#PermissionHolderId,
  scope: ScopeQualifier,
  extraClaims: Option[JsObject]
) extends ResourceAccessRequest(resourceId, Set(scope), extraClaims)

object SingleScopeResourceAccessRequest {

  def apply(
    resourceId: AccessRequest#PermissionHolderId,
    scope: ScopeQualifier,
    extraClaims: Option[JsObject]
  ): SingleScopeResourceAccessRequest = new SingleScopeResourceAccessRequest(resourceId, scope, extraClaims)

  trait ToSingleScopeResourceAccessRequest extends ResourceAccessRequest.ToResourceAccessRequest {
    def toAccessRequest(extraClaims: Option[JsObject]): SingleScopeResourceAccessRequest
  }

}
