
/**
 * @author Santosh
 */

import JsonProtocol.BlockJsonFormat
import spray.json.enrichAny

import scala.collection.mutable.ArrayBuffer


case class Blockchain() {

  val difficulty = 3
  val chain = ArrayBuffer(createGenesisBlock())

  def createGenesisBlock() = {
    val result = Block(System.currentTimeMillis(), List(), "")
    println(result.toJson.toString())
    result
  }

  def getLatestBlock(): Block = {
    chain.last
  }

  def addBlock(newBlock: Block): List[Block] = {

    newBlock.previousHash = getLatestBlock().currentHash
    newBlock.mineBlock(difficulty)
    //newBlock.currentHash = newBlock.calculateHash()
    val result = chain += newBlock
    result.toList
  }

  def isChainValid(): Boolean = {

    for (i <- 0 to chain.length) {

      val currentBlock = chain(i)
      val previousBlock = chain(i - 1)

      if (currentBlock.currentHash != currentBlock.calculateHash()) {
        return false
      }

      if (currentBlock.previousHash != previousBlock.currentHash) {
        return false
      }

    }
    true
  }
}



