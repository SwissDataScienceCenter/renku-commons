package ch.datascience.service.models.deployment.json

import ch.datascience.service.models.deployment.ContainerDeploymentOptions
import play.api.libs.functional.syntax._
import play.api.libs.json._

object ContainerDeploymentOptionsMappers {

  def ContainerDeploymentOptionsFormat: OFormat[ContainerDeploymentOptions] = (
    (JsPath \ "backend").formatNullable[String] and
      (JsPath \ "image").format[String] and
      (JsPath \ "environment").formatNullable[Map[String, String]] and
      (JsPath \ "ports").formatNullable[Map[String, String]]
  ) (read, write)

  private[this] def read(backend: Option[String], image: String, environment: Option[Map[String, String]], ports: Option[Map[String, String]]): ContainerDeploymentOptions = {
    ContainerDeploymentOptions(backend, image, environment.getOrElse(Map.empty), ports.getOrElse(Map.empty))
  }

  private[this] def write(options: ContainerDeploymentOptions): (Option[String], String, Option[Map[String, String]], Option[Map[String, String]]) = {
    (options.backend, options.image, Some(options.environment), Some(options.ports))
  }

}
