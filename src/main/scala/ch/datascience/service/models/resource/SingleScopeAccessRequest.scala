package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
 * Created by johann on 14/07/17.
 */
class SingleScopeAccessRequest(
    permissionHolderId: Option[AccessRequest#PermissionHolderId],
    scope:              ScopeQualifier,
    extraClaims:        Option[JsObject]
) extends AccessRequest( permissionHolderId, Set( scope ), extraClaims )

object SingleScopeAccessRequest {

  def apply(
      permissionHolderId: Option[AccessRequest#PermissionHolderId],
      scope:              ScopeQualifier,
      extraClaims:        Option[JsObject]
  ): SingleScopeAccessRequest = new SingleScopeAccessRequest( permissionHolderId, scope, extraClaims )

  trait ToSingleScopeAccessRequest extends AccessRequest.ToAccessRequest {
    def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest
  }

}
