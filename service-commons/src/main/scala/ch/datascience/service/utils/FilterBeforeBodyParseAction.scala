package ch.datascience.service.utils

import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by johann on 11/07/17.
  */
class FilterBeforeBodyParseAction(filter: (RequestHeader) => Option[Result]) extends ActionBuilder[Request] {

//  def filter(rh: RequestHeader): Option[Result]

  override protected def composeParser[A](bodyParser: BodyParser[A]): BodyParser[A] = new BodyParser[A] {
    def apply(rh: RequestHeader): Accumulator[ByteString, Either[Result, A]] = {
      filter(rh) match {
        case Some(result) => makeError[A](result).apply(rh)
        case None => bodyParser(rh)
      }
    }
  }

  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = block(request)

  private[this] def makeError[A](result: Result): BodyParser[A] = BodyParsers.parse.error( Future.successful( result ) )

}
