package ch.datascience.service.models.resources

/**
  * Created by jeberle on 09.06.17.
  */
case class ReadResourceRequest(resourceId: Long) {

  def toResourceRequest: ResourceRequest = ResourceRequest(resourceId, ResourceScope.StorageRead)

}
