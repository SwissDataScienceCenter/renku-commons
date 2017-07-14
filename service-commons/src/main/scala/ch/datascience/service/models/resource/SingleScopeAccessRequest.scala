package ch.datascience.service.models.resource

/**
  * Created by johann on 14/07/17.
  */
abstract class SingleScopeAccessRequest(
  val permissionHolderId: Option[AccessRequest#PermissionHolderId],
  val scope: ResourceScope
) extends AccessRequest.ToAccessRequest {

  def toAccessRequest: AccessRequest = AccessRequest(permissionHolderId, scope)

}
