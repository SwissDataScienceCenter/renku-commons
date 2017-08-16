package ch.datascience.service.security

import ch.datascience.service.utils.AbstractFilterBeforeBodyParseAction
import com.auth0.jwt.exceptions._
import com.auth0.jwt.{ JWT, JWTVerifier }
import play.api.mvc._

import scala.concurrent.Future
import scala.util.Try
import scala.util.matching.Regex

/**
 * Created by johann on 13/07/17.
 */
object ProfileFilterAction {

  def apply( verifier: JWTVerifier, realm: String, altVerifiers: JWTVerifier* ): ActionBuilder[RequestWithProfile] = {
    TokenFilterAction( verifier, realm, altVerifiers: _* ) andThen ProfileAction
  }

  def apply( verifier: JWTVerifier, altVerifiers: JWTVerifier* ): ActionBuilder[RequestWithProfile] = {
    TokenFilterAction( verifier, realm = "", altVerifiers: _* ) andThen ProfileAction
  }

}
