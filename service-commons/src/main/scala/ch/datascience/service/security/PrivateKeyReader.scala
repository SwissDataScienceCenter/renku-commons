package ch.datascience.service.security

import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64

/**
 * Created by johann on 14/07/17.
 */
object PrivateKeyReader {

  def readRSAPrivateKey( encodedKey: String ): RSAPrivateKey = {
    val decoded = Base64.getDecoder.decode( encodedKey )
    val spec = new PKCS8EncodedKeySpec( decoded )
    val factory = KeyFactory.getInstance( "RSA" )
    val key = factory.generatePrivate( spec )
    key.asInstanceOf[RSAPrivateKey]
  }

}
