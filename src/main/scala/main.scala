import BlockActor.GenesisBlock
import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration.DurationInt


/**
 * @author Santosh
 */
object main extends App {

  implicit val timeout: Timeout = Timeout(20 seconds)

  implicit val system: ActorSystem = ActorSystem("chain")

  val blockActor: ActorRef = system.actorOf(BlockActor.props())

  blockActor ? GenesisBlock


  //  blockActor ! PoisonPill
}
