package ch.datascience.service.models.resource

import com.auth0.jwt.JWTVerifier
import play.api.libs.json.{ JsObject, Json }

case class AccessGrant( accessToken: String ) {

  def verifyAccessToken( verifier: JWTVerifier ): AccessGrantToken = {
    val jwt = verifier.verify( accessToken )

    val resourceIdClaim = jwt.getClaim( "resource_id" )
    val resourceId = if ( resourceIdClaim.isNull ) None else Some( Long.unbox( resourceIdClaim.asLong() ) )

    val scopeArray = jwt.getClaim( "resource_scope" ).asArray( classOf[String] )
    val scope = scopeArray.map( ScopeQualifier.apply ).toSet

    val extraClaim = jwt.getClaim( "resource_extras" )
    val extraAsString = if ( extraClaim.isNull ) None else Some( extraClaim.asString() )
    val extra = extraAsString.map( Json.parse ).map( _.as[JsObject] )

    AccessGrantToken( resourceId, scope, extra, jwt )
  }

}
