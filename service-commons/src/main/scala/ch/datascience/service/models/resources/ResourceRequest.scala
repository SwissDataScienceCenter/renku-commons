package ch.datascience.service.models.resources

/**
  * Created by jeberle on 09.06.17.
  */
case class ResourceRequest(resourceId: Long, scopes: Set[ResourceScope])

object ResourceRequest {

  def apply(resourceId: Long, scopes: ResourceScope*): ResourceRequest = ResourceRequest(resourceId, scopes.toSet)

}
