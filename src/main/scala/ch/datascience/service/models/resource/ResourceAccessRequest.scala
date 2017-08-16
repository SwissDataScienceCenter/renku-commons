package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
 * Created by johann on 14/07/17.
 */
class ResourceAccessRequest(
    resourceId:  AccessRequest#PermissionHolderId,
    scope:       Set[ScopeQualifier],
    extraClaims: Option[JsObject]
) extends AccessRequest( Some( resourceId ), scope, extraClaims )

object ResourceAccessRequest {

  def apply(
      resourceId:  AccessRequest#PermissionHolderId,
      scope:       Set[ScopeQualifier],
      extraClaims: Option[JsObject]
  ): ResourceAccessRequest = new ResourceAccessRequest( resourceId, scope, extraClaims )

  trait ToResourceAccessRequest extends AccessRequest.ToAccessRequest {
    def toAccessRequest( extraClaims: Option[JsObject] ): ResourceAccessRequest
  }

}
