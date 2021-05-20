/**
 * @author Santosh
 */

import JsonProtocol.{BlockJsonFormat, StringJsonFormat, seqFormat}
import spray.json.enrichAny

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

  def getHash(index: Int, previousHash: String, timestamp: Long, data: String): String = {
    Crypto.sha256Hash(index + previousHash + timestamp + data.toJson)
  }

  def mineBlock(difficulty: Int) = {


  }
  def getLatestBlock: Block = {
    blockchainData.last

  }

  def addBlock(): Seq[Block] = {

    val previousHash = getLatestBlock.hash
    val hash = getHash(getLatestBlock.index, previousHash, getLatestBlock.timestamp, getLatestBlock.transaction)

    blockchainData = blockchainData ++ Seq(Block(getLatestBlock.index + 1, System.currentTimeMillis(), "random-data", previousHash, hash))

    println(blockchainData.toJson.toString())
    blockchainData
  }

  override var data: String = _
  override var previousHash: String = _
  override var hash: String = _
}

