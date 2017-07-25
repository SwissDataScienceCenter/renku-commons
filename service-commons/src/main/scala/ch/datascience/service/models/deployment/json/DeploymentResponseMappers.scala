package ch.datascience.service.models.deployment.json

import ch.datascience.service.models.deployment.DeploymentResponse
import play.api.libs.functional.syntax._
import play.api.libs.json._

object DeploymentResponseMappers {

  def DeploymentResponseFormat: OFormat[DeploymentResponse] = (
    (JsPath \ "id").format[Long] and
      (JsPath \ "backend_id").formatNullable[String]
  ) (DeploymentResponse, unlift(DeploymentResponse.unapply))

}
