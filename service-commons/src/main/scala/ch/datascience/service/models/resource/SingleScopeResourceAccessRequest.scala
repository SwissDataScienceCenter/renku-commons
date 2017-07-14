package ch.datascience.service.models.resource

/**
  * Created by johann on 14/07/17.
  */
abstract class SingleScopeResourceAccessRequest(
                                                 val resourceId: AccessRequest#PermissionHolderId,
                                                 val scope: ResourceScope
) extends ResourceAccessRequest.ToResourceAccessRequest {

  override def toResourceAccessRequest: ResourceAccessRequest = ResourceAccessRequest(resourceId, scope)

}
