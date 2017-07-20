package ch.datascience.service.models.resource

import play.api.libs.json.{Format, OFormat}

/**
  * Created by johann on 25/04/17.
  */
package object json {

  implicit lazy val AccessRequestFormat: OFormat[AccessRequest] = AccessRequestMappers.AccessRequestFormat
  implicit lazy val AccessGrantFormat: OFormat[AccessGrant] = AccessGrantMappers.AccessGrantFormat

  implicit lazy val ScopeQualifierFormat: Format[ScopeQualifier] = ScopeQualifierMappers.ScopeQualifierFormat

}
