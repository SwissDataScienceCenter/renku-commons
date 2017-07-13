package ch.datascience.service.security

import ch.datascience.service.utils.AbstractFilterBeforeBodyParseAction
import com.auth0.jwt.{JWT, JWTVerifier}
import com.auth0.jwt.exceptions._
import play.api.mvc._

import scala.concurrent.Future
import scala.util.Try
import scala.util.matching.Regex

/**
  * Created by johann on 13/07/17.
  */
case class TokenFilterAction(verifier: JWTVerifier, realm: String = "")
  extends ActionBuilder[RequestWithToken]
    with ActionTransformer[Request, RequestWithToken]
    with AbstractFilterBeforeBodyParseAction {

  protected def transform[A](request: Request[A]): Future[RequestWithToken[A]] = Future.successful {
    val token = JWT.decode(extractToken(request.headers).get)
    new RequestWithToken[A](token, request)
  }

  def filter(rh: RequestHeader): Option[Result] = {
    extractToken(rh.headers) match {
      case Some(token) => val t = Try { verifier.verify(token) }
        t.map(_ => None).recover {
          case e: JWTDecodeException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Token is not a JWT")))
          case e: AlgorithmMismatchException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Algorithm mismatch")))
          case e: SignatureVerificationException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Token signature invalid")))
          case e: TokenExpiredException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Token expired")))
          case e: InvalidClaimException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Claims do not match verifier")))
        }.get
      case None => Some(makeUnauthorizedResponse())
    }
  }

  protected def extractToken(headers: Headers): Option[String] = {
    headers.get("Authorization") match {
      case Some(header) =>  header match {
        case tokenRegexp(token) => Some(token)
        case _ => None
        case _ => None
      }
      case None => None
    }
  }

  protected lazy val tokenRegexp: Regex = "(?i)Bearer (.*)/i".r.anchored

  protected def makeUnauthorizedResponse(error: Option[String] = None, errorDescription: Option[String] = None): Result = {
    val errorMsg = error.map(e => s", error=\"$e\"").getOrElse("")
    val errorDescriptionMsg = errorDescription.map(e => s", error_description=\"$e\"").getOrElse("")
    val challenge =  s"Bearer realm=\"$realm\"$errorMsg$errorDescriptionMsg"
    Results.Unauthorized.withHeaders(("WWW-Authenticate", challenge))
  }

}
