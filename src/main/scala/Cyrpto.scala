import java.math.BigInteger
import java.security.MessageDigest

/**
 * procedure for creating SHA256 code
 * sourced from an existing github repo
 */
object Crypto {

  def sha256Hash(value: String) = String.format("%064x",
    new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(value.getBytes("UTF-8"))))

}
