package ch.datascience.service.models.resource

/**
  * Created by jeberle on 09.06.17.
  */
case class WriteResourceRequest(override val resourceId: AccessRequest#PermissionHolderId)
  extends SingleScopeResourceAccessRequest(resourceId, scope = WriteResourceRequest.scope)

object WriteResourceRequest {
  lazy val scope: ResourceScope = ResourceScope.StorageWrite
}
