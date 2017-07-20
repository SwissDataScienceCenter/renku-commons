package ch.datascience.service.models.deployment

import play.api.libs.json.OFormat

package object json {

  implicit lazy val ContainerDeploymentOptionsFormat: OFormat[ContainerDeploymentOptions] = ContainerDeploymentOptionsMappers.ContainerDeploymentOptionsFormat
  implicit lazy val DeploymentRequestFormat: OFormat[DeploymentRequest] = DeploymentRequestMappers.DeploymentRequestFormat

}
