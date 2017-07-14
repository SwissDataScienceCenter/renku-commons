package ch.datascience.service.models.resource

/**
  * Created by johann on 13/07/17.
  */
case class CreateFileRequest(bucketId: AccessRequest#PermissionHolderId, fileName: String)
  extends SingleScopeAccessRequest(permissionHolderId = Some(bucketId), scope = CreateFileRequest.scope)

object CreateFileRequest {
  lazy val scope: ResourceScope = ResourceScope.StorageCreate
}
