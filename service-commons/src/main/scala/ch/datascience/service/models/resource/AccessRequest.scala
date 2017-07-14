package ch.datascience.service.models.resource

/**
  * Created by johann on 14/07/17.
  */
case class AccessRequest(
  permissionHolderId: Option[AccessRequest#PermissionHolderId],
  scopes: Set[ResourceScope]
) {

  type PermissionHolderId = Long

}

object AccessRequest {

  def apply(
    permissionHolderId: Option[AccessRequest#PermissionHolderId],
    scopes: ResourceScope*
  ): AccessRequest = AccessRequest(permissionHolderId, scopes.toSet)

  trait ToAccessRequest {
    def toAccessRequest: AccessRequest
  }

}
