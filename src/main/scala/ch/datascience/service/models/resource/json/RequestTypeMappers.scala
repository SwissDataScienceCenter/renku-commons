package ch.datascience.service.models.resource.json

import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
 * Created by johann on 18/07/17.
 */
object RequestTypeMappers {

  def format[A]( requestType: String )( format: OFormat[A] ): OFormat[A] = OFormat( readFilter( requestType )( format ), writeWithType( requestType )( format ) )

  def readFilter[A]( requestType: String )( reads: Reads[A] ): Reads[A] = {
    val r1 = ( JsPath \ "request_type" ).read[String].filter( ValidationError( "wrong type" ) )( _ == requestType )
    r1.flatMap { _ => reads }
  }

  def writeWithType[A]( requestType: String )( writes: OWrites[A] ): OWrites[A] = {
    val t: OWrites[JsObject] = (
      ( JsPath \ "request_type" ).write[String] and
      JsPath.write[JsObject]
    ) { x => ( requestType, x ) }
    writes.transform( t )
  }

}
