/**
 * @author Santosh
 */
/*
import BlockActor.{AddBlock, GenesisBlock, GetLatestBlock}
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.stream.ActorMaterializer
import spray.json.enrichAny
import scala.concurrent.ExecutionContext

/**
 * Actor System for creating/ managing Blocks
 */


class BlockActor extends Actor with ActorLogging {

  implicit val system: ActorSystem = ActorSystem("Blockchain")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher

  val blockchain: Blockchain = Blockchain()

  def receive: PartialFunction[Any, Unit] = {

    case GenesisBlock =>
      //val result =
      blockchain.genesisBlock
    // sender ! (s"${result.foreach((e: Block) => print(e.toJson))}")

   /* case GetLatestBlock =>
      blockchain.getLatestBlock

    case AddBlock =>
      val result = blockchain.addBlock()
      sender ! s"${result.foreach((e: Block) => print(e.toJson))}"*/
    case x => log.warning("Received unknown message: [{}] ", x)

  }
}

object BlockActor {

  def props(): Props = Props(new BlockActor)

  case object GenesisBlock

  case object GetLatestBlock

  case object AddBlock


}
*/


