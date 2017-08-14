package controllers

import authorization.{ JWTVerifierProvider, MockJWTVerifierProvider, MockTokenSignerProvider, TokenSignerProvider }
import ch.datascience.service.models.resource.json._
import ch.datascience.service.models.resource.{ AccessGrant, AccessRequest, ScopeQualifier }
import ch.datascience.service.security.FakeRequestWithToken._
import com.auth0.jwt.JWT
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.JsValue
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future

class PermissionControllerSpec extends PlaySpec with OneAppPerSuite with MockitoSugar with Results {

  override def fakeApplication(): Application = new GuiceApplicationBuilder()
    .overrides( bind[JWTVerifierProvider].to[MockJWTVerifierProvider] )
    .overrides( bind[TokenSignerProvider].to[MockTokenSignerProvider] )
    .build()

  val permissionController: PermissionController = app.injector.instanceOf[PermissionController]

  "PermissionController#authorize" should {
    "returns a scope subset from the request" in {
      val testedScope: Set[ScopeQualifier] = Set( ScopeQualifier.BucketCreate )

      val tokenBuilder = JWT.create()
      val tokenSignerProvider = app.injector.instanceOf[TokenSignerProvider]
      tokenSignerProvider.addDefaultHeadersAndClaims( tokenBuilder )
      val token = tokenBuilder.sign( tokenSignerProvider.get )

      val accessRequest = AccessRequest( None, testedScope, None )

      val result: Future[Result] = permissionController.authorize().apply( FakeRequest().withToken( token ).withBody( accessRequest ) )

      val bodyResult: JsValue = contentAsJson( result )
      val grant: AccessGrant = bodyResult.as[AccessGrant]
      val returnedToken = grant.verifyAccessToken( app.injector.instanceOf[JWTVerifierProvider].get )

      val returnedScope = returnedToken.scope

      // Test that returnedScope is a subset of testedScope (scope passed in request)
      testedScope must contain allElementsOf returnedScope
    }

  }

}
