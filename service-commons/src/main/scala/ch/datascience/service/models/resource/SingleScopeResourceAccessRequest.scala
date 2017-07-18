package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by johann on 14/07/17.
  */
abstract class SingleScopeResourceAccessRequest(
  val resourceId: AccessRequest#PermissionHolderId,
  val scope: ScopeQualifier,
  val extraClaims: Option[JsObject]
) extends ResourceAccessRequest.ToResourceAccessRequest {

  override def toResourceAccessRequest: ResourceAccessRequest = ResourceAccessRequest(resourceId, Set(scope), extraClaims)

}
