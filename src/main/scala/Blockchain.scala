/**
 * @author Santosh
 */


import scala.collection.mutable.{ArrayBuffer}


case class Blockchain() {


  val chain = ArrayBuffer(createGenesisBlock())

  def createGenesisBlock(): Block = {
    Block(System.currentTimeMillis(), List(), "")
  }

  def getLatestBlock(): Block = {
    chain.last
  }

  def addBlock(newBlock: Block): List[Block] = {

    println(getLatestBlock().previousHash, getLatestBlock().currentHash)
    newBlock.previousHash = getLatestBlock().currentHash
    newBlock.currentHash = newBlock.calculateHash()
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



