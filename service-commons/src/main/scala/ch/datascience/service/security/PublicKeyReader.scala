package ch.datascience.service.security

import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

/**
  * Created by johann on 14/07/17.
  */
object PublicKeyReader {

  def readRSAPublicKey(encodedKey: String): RSAPublicKey = {
    val decoded = Base64.getDecoder.decode(encodedKey)
    val spec = new X509EncodedKeySpec(decoded)
    val factory = KeyFactory.getInstance("RSA")
    val key = factory.generatePublic(spec)
    key.asInstanceOf[RSAPublicKey]
  }

}
