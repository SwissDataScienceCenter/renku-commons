package ch.datascience.test.security

import play.api.mvc.RequestHeader
import play.api.test.FakeRequest

object FakeRequestWithToken {

  implicit class RichFakeRequest[A]( underlying: FakeRequest[A] ) {

    def withToken( token: String ): FakeRequest[A] = {
      val newTags: Map[String, String] = underlying.tags + ( "VERIFIED_TOKEN" -> token )

      underlying.copyFakeRequest( tags = newTags ).withHeaders( "Authorization" -> s"Bearer $token" )
    }

  }

}
