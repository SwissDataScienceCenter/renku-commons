package ch.datascience.service.models.resources

/**
  * Created by jeberle on 09.06.17.
  */
case class CreateBucketRequest(name: String, backend: String) extends ResourceRequestDetails
