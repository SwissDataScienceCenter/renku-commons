/*
 * Copyright 2017 - Swiss Data Science Center (SDSC)
 * A partnership between École Polytechnique Fédérale de Lausanne (EPFL) and
 * Eidgenössische Technische Hochschule Zürich (ETHZ).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
case class TokenFilterAction(verifier: JWTVerifier, realm: String, altVerifiers: JWTVerifier*)
  extends ActionBuilder[RequestWithToken]
    with ActionTransformer[Request, RequestWithToken]
    with AbstractFilterBeforeBodyParseAction {

  protected def transform[A](request: Request[A]): Future[RequestWithToken[A]] = Future.successful {
    require(request.tags.get("VERIFIED_TOKEN").contains(extractToken(request.headers).get))
    val token = JWT.decode(extractToken(request.headers).get)
    new RequestWithToken[A](token, request)
  }

  def filter(rh: RequestHeader): Either[Result, RequestHeader] = {
    extractToken(rh.headers) match {
      case Some(token) =>
        val t0 = Try { verifier.verify(token) }
        val t = altVerifiers.foldLeft(t0) { (t, v) => t.orElse(Try{ v.verify(token) }) }
        t.map(_ => None).recover {
          case e: JWTDecodeException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Token is not a JWT")))
          case e: AlgorithmMismatchException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Algorithm mismatch")))
          case e: SignatureVerificationException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Token signature invalid")))
          case e: TokenExpiredException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Token expired")))
          case e: InvalidClaimException => Some(makeUnauthorizedResponse(Some("invalid_token"), Some("Claims do not match verifier")))
        }.get.toLeft(rh.withTag("VERIFIED_TOKEN", token))
      case None => Left(makeUnauthorizedResponse())
    }
  }

  protected def extractToken(headers: Headers): Option[String] = {
    headers.get("Authorization") match {
      case Some(header) =>  header match {
        case tokenRegexp(token) => Some(token)
        case _ => None
      }
      case None => None
    }
  }

  protected lazy val tokenRegexp: Regex = "(?i)Bearer (.*)/i".r.anchored

  protected def makeUnauthorizedResponse(error: Option[String] = None, errorDescription: Option[String] = None): Result = {
    val errorMsg = error.map(e => s""", error="$e"""").getOrElse("")
    val errorDescriptionMsg = errorDescription.map(e => s""", error_description="$e"""").getOrElse("")
    val challenge =  s"""Bearer realm="$realm"$errorMsg$errorDescriptionMsg"""
    Results.Unauthorized.withHeaders(("WWW-Authenticate", challenge))
  }

}

object TokenFilterAction {

  def apply(verifier: JWTVerifier, altVerifiers: JWTVerifier*): TokenFilterAction = TokenFilterAction(verifier, realm = "", altVerifiers: _*)

}
