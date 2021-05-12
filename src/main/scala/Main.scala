/**
 * @author Santosh
 */

import BlockActor.GenesisBlock
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

  blockActor ? GenesisBlock
  blockActor ! PoisonPill
}
