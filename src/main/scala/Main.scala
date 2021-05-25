/**
 * @author Santosh
 */

//import BlockActor.{AddBlock, GenesisBlock, GetLatestBlock}

import JsonProtocol.{BlockJsonFormat, IntJsonFormat, listFormat}
import spray.json.enrichAny
import akka.actor.{ActorRef, ActorSystem, PoisonPill}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

/**
 * Creates an Actor for the main system
 */

object Main extends App {

  implicit val timeout: Timeout = Timeout(20 seconds)

  implicit val system: ActorSystem = ActorSystem("Blockchain")

  //val blockActor: ActorRef = system.actorOf(BlockActor.props())

  val blockchain = Blockchain()

  blockchain.createTransaction(Transaction("address1", "address2", 100))

  blockchain.createTransaction(Transaction("address2", "address1", 50))

  println("starting mining...")

  blockchain.minePendingTransactions("santosh-address")

  println(s"Balance of Santosh is ${blockchain.getBalanceOfAddress("santosh-address")}")

  println("starting mining again...")

  blockchain.minePendingTransactions("santosh-address")

  println(s"Balance of Santosh is ${blockchain.getBalanceOfAddress("santosh-address")}")


}
