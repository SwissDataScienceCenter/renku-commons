package ch.datascience.service.models.resource

/**
  * Created by jeberle on 09.06.17.
  */
case class ReadResourceRequest(override val resourceId: AccessRequest#PermissionHolderId)
  extends SingleScopeResourceAccessRequest(resourceId, scope = ReadResourceRequest.scope)

object ReadResourceRequest {
  lazy val scope: ResourceScope = ResourceScope.StorageRead
}
