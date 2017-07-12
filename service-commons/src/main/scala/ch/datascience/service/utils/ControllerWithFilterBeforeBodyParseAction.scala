package ch.datascience.service.utils

import play.api.mvc._

/**
  * Created by johann on 12/07/17.
  */
trait ControllerWithFilterBeforeBodyParseAction { this: BaseController =>

  def FilterBeforeBodyParseAction(filter: (RequestHeader) => Option[Result]): FilterBeforeBodyParseAction = {
    new FilterBeforeBodyParseActionImpl(filter)
  }

  protected class FilterBeforeBodyParseActionImpl(
    filter: (RequestHeader) => Option[Result]
  ) extends ActionBuilderImpl[AnyContent](parse.default)(defaultExecutionContext)
    with FilterBeforeBodyParseAction {
    def filter(rh: RequestHeader): Option[Result] = filter.apply(rh)
  }

}
