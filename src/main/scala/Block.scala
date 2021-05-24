import JsonProtocol.BlockJsonFormat
import spray.json.enrichAny

/**
 * @author Santosh
 */

/**
  model for a Block
 * @param timestamp: denotes timestamp of the block
 * @param transaction: denotes transactional data of the block
 * @param previousHash: benotes previous SHA256 value of the block
 */
case class Block(var timestamp: Long, var transaction: List[Transaction], var previousHash: String ) {

  // val timeStamp = timestamp
  //val transactions = transaction
  var currentHash = calculateHash()
  var nonce = 0


  def calculateHash():String = {
    Crypto.sha256Hash(this.asInstanceOf[Block].toJson.toString())
  }

}

