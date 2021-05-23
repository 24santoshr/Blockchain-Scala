/**
 * @author Santosh
 */

import JsonProtocol.{BlockJsonFormat, StringJsonFormat, seqFormat}
import shapeless.syntax.std.tuple.productTupleOps
import spray.json.enrichAny

import scala.collection.immutable.Set.EmptySet

case class Blockchain()  {

  var blockchainData: Block = genesisBlock

  def genesisBlock: Block = {

    val timestamp = System.currentTimeMillis()
    val data = "Genesis Block"
    val previousHash = "0"
    val nonce = 0
    val currentHash = getHash(nonce, previousHash, timestamp, data)

    val createdGenesisBlock = (Block(timestamp, List(), previousHash, currentHash))
    createdGenesisBlock
  }

  def getHash(nonce: Int,previousHash: String, timestamp: Long, data: String): String = {
    Crypto.sha256Hash(previousHash + timestamp + data.toJson)
  }

  def mineBlock(difficulty: Int) = {???}




}

