/**
 * @author Santosh
 */

import BlockActor.{AddBlock, GenesisBlock, GetLatestBlock}
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

  val blockActor: ActorRef = system.actorOf(BlockActor.props())

val  blockchain = Blockchain()

  /*blockchain.addBlock()
  blockchain.addBlock()
  blockchain.addBlock()*/

  /*blockActor ? GenesisBlock
  blockActor ? AddBlock
  blockActor ? GetLatestBlock
*/}
