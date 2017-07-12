package ch.datascience.service.utils

import play.api.mvc._

/**
  * Created by johann on 12/07/17.
  */
trait ControllerWithBeforeBodyParseActions { this: BaseController =>

  def RefineBeforeBodyParseAction(refine: (RequestHeader) => Either[Result, RequestHeader]): RefineBeforeBodyParseAction = {
    new RefineBeforeBodyParseActionImpl(refine)
  }

  def TransformBeforeBodyParseAction(transform :(RequestHeader) => RequestHeader): TransformBeforeBodyParseAction = {
    new TransformBeforeBodyParseActionImpl(transform)
  }

  def FilterBeforeBodyParseAction(filter: (RequestHeader) => Option[Result]): FilterBeforeBodyParseAction = {
    new FilterBeforeBodyParseActionImpl(filter)
  }

  protected class RefineBeforeBodyParseActionImpl(refine: (RequestHeader) => Either[Result, RequestHeader]) extends ActionBuilderImpl[AnyContent](parse.default) with RefineBeforeBodyParseAction {
    def refine(rh: RequestHeader): Either[Result, RequestHeader] = refine.apply(rh)
  }

  protected class TransformBeforeBodyParseActionImpl(transform :(RequestHeader) => RequestHeader) extends ActionBuilderImpl[AnyContent](parse.default) with TransformBeforeBodyParseAction {
    def transform(rh: RequestHeader): RequestHeader = transform.apply(rh)
  }

  protected class FilterBeforeBodyParseActionImpl(filter: (RequestHeader) => Option[Result]) extends ActionBuilderImpl[AnyContent](parse.default) with FilterBeforeBodyParseAction {
    def filter(rh: RequestHeader): Option[Result] = filter.apply(rh)
  }

}
