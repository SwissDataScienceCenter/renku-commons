package ch.datascience.service.utils

import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by johann on 11/07/17.
  */
trait RefineBeforeBodyParseAction extends ActionBuilder[Request, AnyContent] {

  def refine(rh: RequestHeader): Either[Result, RequestHeader]

  override protected def composeParser[A](bodyParser: BodyParser[A]): BodyParser[A] = new BodyParser[A] {
    def apply(rh: RequestHeader): Accumulator[ByteString, Either[Result, A]] = {
      refine(rh).fold(makeError[A](_).apply(rh), bodyParser)
    }
  }

  private[this] def makeError[A](result: Result): BodyParser[A] = BodyParsers.utils.error( Future.successful( result ) )

}

trait TransformBeforeBodyParseAction extends RefineBeforeBodyParseAction {

  def transform(rh: RequestHeader): RequestHeader

  final def refine(rh: RequestHeader): Either[Result, RequestHeader] = Right(transform(rh))

}

trait FilterBeforeBodyParseAction extends RefineBeforeBodyParseAction {

  def filter(rh: RequestHeader): Option[Result]

  final def refine(rh: RequestHeader): Either[Result, RequestHeader] = filter(rh).toLeft(rh)

}
