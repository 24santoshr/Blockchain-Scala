import JsonProtocol.BlockJsonFormat
import spray.json.enrichAny

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * @author Santosh
 */

/**
 * model for a Block
 *
 * @param timestamp    : denotes timestamp of the block
 * @param transaction  : denotes transactional data of the block
 * @param previousHash : benotes previous SHA256 value of the block
 */
case class Block(var timestamp: Long, var transaction: Seq[Transaction], var previousHash: String = " ") {

  var currentHash = calculateHash()
  var nonce = 0


  def calculateHash(): String = {
    //Crypto.sha256Hash(this.asInstanceOf[Block].toJson.toString())
    Crypto.sha256Hash((this.previousHash + this.timestamp + this.nonce))

  }

  def mineBlock(difficulty: Int): Unit = {

    while (this.currentHash.substring(0, difficulty) != "000") { //need to change hardcoded value
      nonce += 1
      this.currentHash = calculateHash()
    }
    println(s"Block mined  and its hash is ${this.currentHash}")
  }

}

