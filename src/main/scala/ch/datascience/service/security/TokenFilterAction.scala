/*
 * Copyright 2017-2018 - Swiss Data Science Center (SDSC)
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

import ch.datascience.service.utils.FilterBeforeBodyParseAction
import com.auth0.jwt.{ JWT, JWTVerifier }
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ ExecutionContext, Future }

/**
 * Created by johann on 13/07/17.
 */
trait TokenFilterAction[B]
  extends ActionBuilder[RequestWithToken, B]
  with ActionTransformer[Request, RequestWithToken]
  with FilterBeforeBodyParseAction[B] {

  def verifier: JWTVerifier

  def realm: String

  def altVerifiers: Seq[JWTVerifier]

  lazy val tokenFilter: TokenFilter = TokenFilter( verifier, realm, altVerifiers: _* )

  protected def transform[A]( request: Request[A] ): Future[RequestWithToken[A]] = Future.successful {
    require( request.attrs.get( VerifiedBearerToken ).contains( JWT.decode( tokenFilter.extractToken( request.headers ).get ) ) )
    val token = JWT.decode( tokenFilter.extractToken( request.headers ).get )
    new RequestWithToken[A]( token, request )
  }

  def filter( rh: RequestHeader ): Either[Result, RequestHeader] = {
    tokenFilter.filter( rh ).right.map( token => rh.withTag( "VERIFIED_TOKEN", token.getToken ) )
  }

}

class TokenFilterActionBuilder @Inject() (
    parser: BodyParsers.Default
)(
    implicit
    executionContext: ExecutionContext
) {

  def apply( verifier: JWTVerifier, realm: String, altVerifiers: JWTVerifier* ): TokenFilterAction[AnyContent] = {
    TokenFilterActionImpl( verifier, realm, altVerifiers, parser )
  }

  def apply( verifier: JWTVerifier, altVerifiers: JWTVerifier* ): TokenFilterAction[AnyContent] = apply( verifier, realm = "", altVerifiers: _* )

}

case class TokenFilterActionImpl[B](
    verifier: JWTVerifier,
    realm:    String,

    altVerifiers: Seq[JWTVerifier],
    parser:       BodyParser[B]
)(
    implicit
    val executionContext: ExecutionContext
) extends TokenFilterAction[B]
