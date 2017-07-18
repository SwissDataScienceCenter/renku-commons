package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by johann on 14/07/17.
  */
case class ResourceAccessRequest(
  resourceId: AccessRequest#PermissionHolderId,
  scope: Set[ScopeQualifier],
  extraClaims: Option[JsObject]
) extends AccessRequest.ToAccessRequest {

  override def toAccessRequest: AccessRequest = AccessRequest(Some(resourceId), scope, extraClaims)

}

object ResourceAccessRequest {

  trait ToResourceAccessRequest extends AccessRequest.ToAccessRequest {
    def toResourceAccessRequest: ResourceAccessRequest

    final def toAccessRequest: AccessRequest = toResourceAccessRequest.toAccessRequest
  }

}
