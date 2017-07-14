package ch.datascience.service.models.resource

/**
  * Created by johann on 14/07/17.
  */
case class ResourceAccessRequest(
  resourceId: AccessRequest#PermissionHolderId,
  scopes: Set[ResourceScope]
) extends AccessRequest.ToAccessRequest {

  override def toAccessRequest: AccessRequest = AccessRequest(Some(resourceId), scopes)

}

object ResourceAccessRequest {

  def apply(
    resourceId: AccessRequest#PermissionHolderId,
    scopes: ResourceScope*
  ): ResourceAccessRequest = ResourceAccessRequest(resourceId, scopes.toSet)

  trait ToResourceAccessRequest extends AccessRequest.ToAccessRequest {
    def toResourceAccessRequest: ResourceAccessRequest

    final def toAccessRequest: AccessRequest = toResourceAccessRequest.toAccessRequest
  }

}
