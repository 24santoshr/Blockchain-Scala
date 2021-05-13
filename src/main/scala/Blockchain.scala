/**
 * @author Santosh
 */

import JsonProtocol.StringJsonFormat
import spray.json.enrichAny

case class Blockchain() {

  var blockchainData: Seq[Block] = genesisBlock

  def genesisBlock: Seq[Block] = {
    val index = 0
    val timestamp = System.currentTimeMillis()
    val data = "Genesis Block"
    val previousHash = "0"
    val currentHash = getHash(index, previousHash, timestamp, data)

    val createdGenesisBlock = Seq(Block(index, timestamp, data, previousHash, currentHash))
    createdGenesisBlock
  }

  def getHash(index: Int, previousHash: String, timestamp: Long, data: String): String = {
    Crypto.sha256Hash(index + previousHash + timestamp + data.toJson)
  }

  def getLatestBlock: Block = {
    blockchainData.last

  }

  def addBlock(): Seq[Block] = {

    val previousHash = getLatestBlock.hash
    val hash = getHash(getLatestBlock.index, previousHash, getLatestBlock.timestamp, getLatestBlock.data)

    blockchainData = blockchainData ++ Seq(Block(getLatestBlock.index + 1, System.currentTimeMillis(), "random-data", previousHash, hash))

    blockchainData
  }
}