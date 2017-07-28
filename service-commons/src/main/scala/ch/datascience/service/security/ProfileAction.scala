package ch.datascience.service.security

import play.api.mvc.ActionTransformer

import scala.concurrent.Future
import scala.util.Try

/**
 * Created by johann on 18/07/17.
 */
object ProfileAction extends ActionTransformer[RequestWithToken, RequestWithProfile] {

  protected def transform[A]( request: RequestWithToken[A] ): Future[RequestWithProfile[A]] = {
    val executionId = request.headers.get( "SDSC-DEPLOYMENT-ID" ).map( x => Try { Some( x.toLong ) } ).flatMap( _.getOrElse( None ) )
    Future.successful( new RequestWithProfile[A]( request.token, executionId, request ) )
  }

}
