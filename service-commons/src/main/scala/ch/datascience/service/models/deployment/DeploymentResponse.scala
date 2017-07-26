package ch.datascience.service.models.deployment

import play.api.libs.json.JsObject

/**
  * Created by johann on 10/07/17.
  */
case class DeploymentResponse(id: Long, backendId: Option[String])
