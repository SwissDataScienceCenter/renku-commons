package ch.datascience.service.security

import com.auth0.jwt.interfaces.DecodedJWT
import play.api.mvc.{Request, WrappedRequest}

/**
  * Created by johann on 13/07/17.
  */
class RequestWithProfile[+A](token: DecodedJWT, val executionId: Option[Long], request: Request[A]) extends RequestWithToken[A](token, request) {

  def userId: String = token.getSubject

}
