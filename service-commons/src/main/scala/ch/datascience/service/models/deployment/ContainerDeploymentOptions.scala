package ch.datascience.service.models.deployment

import play.api.libs.json.{JsObject, JsString}

/**
  * Created by johann on 10/07/17.
  */
case class ContainerDeploymentOptions(
  backend: Option[String],
  image: String,
  environment: Map[String, String],
  ports: Map[String, String],
  entrypoint: Option[String],
  command: Option[Seq[String]]
)
