package ch.datascience.service.models.deployment.json

import ch.datascience.service.models.deployment.DeploymentRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._

object DeploymentRequestMappers {

  def DeploymentRequestFormat: OFormat[DeploymentRequest] = (
    (JsPath \ "deployment_type").format[String] and
      (JsPath \ "options").formatNullable[JsObject]
  ) (DeploymentRequest.apply, unlift(DeploymentRequest.unapply))

}
