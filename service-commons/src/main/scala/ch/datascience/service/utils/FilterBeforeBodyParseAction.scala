package ch.datascience.service.utils

import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by johann on 11/07/17.
  */
trait AbstractFilterBeforeBodyParseAction extends ActionBuilder[Request] {

  protected def filter(rh: RequestHeader): Either[Result, RequestHeader]

  override protected def composeParser[A](bodyParser: BodyParser[A]): BodyParser[A] = new BodyParser[A] {
    def apply(rh: RequestHeader): Accumulator[ByteString, Either[Result, A]] = {
      filter(rh) match {
        case Left(result) => makeError[A](result).apply(rh)
        case Right(newRH) => bodyParser(newRH)
      }
    }
  }

  private[this] def makeError[A](result: Result): BodyParser[A] = BodyParsers.parse.error( Future.successful( result ) )

}

case class FilterBeforeBodyParseAction(filter: (RequestHeader) => Either[Result, RequestHeader]) extends AbstractFilterBeforeBodyParseAction {

  protected def filter(rh: RequestHeader): Either[Result, RequestHeader] = filter.apply(rh)

  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = block(request)

}
