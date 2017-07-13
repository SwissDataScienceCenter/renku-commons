package ch.datascience.service.models.resources

/**
  * Created by johann on 13/07/17.
  */
case class CreateFileRequest(bucketId: Long, fileName: String)
  extends CreateResourceRequest(createPermissionHolder = bucketId)
