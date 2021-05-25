
/**
 * @author Santosh
 */

import JsonProtocol.BlockJsonFormat
import akka.actor.Address
import spray.json.enrichAny

import java.util
import java.util.{Calendar, Date}
import scala.collection.mutable.ArrayBuffer


case class Blockchain() {

  val difficulty = 3
  val chain = ArrayBuffer(createGenesisBlock())
  var pendingTransactions = Seq[Transaction]()
  var miningReward = 100

  def createGenesisBlock() = {
    val result = Block(System.currentTimeMillis(), List(), "")
    println(result.toJson.toString())
    result
  }

  def getLatestBlock(): Block = {
    chain.last
  }

  def minePendingTransactions(mineRewardAddress: String) = {

    val block = Block(Calendar.getInstance().getTimeInMillis, this.pendingTransactions)
    block.mineBlock(this.difficulty)
    println("Block successfully mined")
    chain += block
    this.pendingTransactions = this.pendingTransactions ++ Seq(Transaction(null, mineRewardAddress, this.miningReward))

  }

  def createTransaction(transaction: Transaction): Unit = {

    this.pendingTransactions ++ Seq(transaction)
  }

  def getBalanceOfAddress(address: String) = {
    var balance: Long = 0

    for (i <- this.chain) {
      for (k <- i.transaction) {
        if (k.fromAddress == address) {
          balance -= k.amount
        }
        if (k.toAddress == address) {
          balance += k.amount
        }
      }
    }
    balance

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



