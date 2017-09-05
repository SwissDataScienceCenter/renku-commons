package ch.datascience.service.swagger

import play.api.Configuration
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsObject
import play.api.mvc._

import scala.concurrent.Future

trait SwaggerControllerHelper { this: Controller =>

  def config: Configuration

  def swaggerSpec: JsObject

  def getSwagger: Action[Unit] = Action.async( BodyParsers.parse.empty ) { implicit request =>
    Future {
      val host = extractHost( request )
      val scheme = extractScheme( request )

      val content = swaggerSpec.transform( SwaggerMappers.updateHostSchemesAndAuthorizationUrl( host, List( scheme ), authorizationUrl ) ).get
      Ok( content )
    }
  }

  lazy val authorizationUrl: String = config.getString( "swagger.authorization.provider.url" ).get

  protected def extractHost[A]( request: Request[A] ): String = {
    request.headers.get( "X-Forwarded-Host" ).getOrElse(
      request.host
    )
  }

  protected def extractScheme[A]( request: Request[A] ): String = {
    request.headers.get( "X-Forwarded-Proto" ).getOrElse(
      if ( request.secure )
        "https"
      else
        "http"
    )
  }

}
