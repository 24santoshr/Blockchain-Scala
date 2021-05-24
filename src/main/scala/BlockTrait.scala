/*

import spray.json._
import JsonProtocol.{ChainLinkJsonFormat, listFormat}

import java.security.InvalidParameterException

sealed trait BlockTrait {

  val timestamp: Long
  val transaction: List[Transaction]
  val hash: String
  val nonce: Int

  def ::(link: BlockTrait): BlockTrait = link match {
    case l:ChainLink => ChainLink(l.nonce, l.transaction, this.hash, l.timestamp, this)
    case _ => throw new InvalidParameterException("Cannot add invalid link to chain")
  }

}

case class ChainLink(nonce: Int, transaction: List[Transaction],previousHash: String = "", timestamp: Long = System.currentTimeMillis(), tail: BlockTrait = FirstBlock) extends BlockTrait {
  val hash = Crypto.sha256Hash(this.toString())
}

case object FirstBlock extends BlockTrait {

  val timestamp = System.currentTimeMillis()
  val transaction = Nil
  val hash = "0"
  val nonce = 0
}

case class MineBlock(difficulty: Int) extends BlockTrait {


}
*/
