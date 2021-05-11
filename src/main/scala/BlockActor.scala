import BlockActor.GenesisBlock
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.util.Success

/**
 * @author Santosh
 */
class BlockActor extends Actor with ActorLogging {

  implicit val system: ActorSystem = ActorSystem("Blockchain")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher


  def receive: PartialFunction[Any, Unit] = {

    case GenesisBlock =>
      sender ! Success(self.path)
      log.info("Genesis Block created")

    case x => log.warning("Received unknown message: [{}] ", x)

  }
}

object BlockActor  {

  def props(): Props = Props(new BlockActor)

  case class GenesisBlock() {
   val result: Block = Block(0, System.currentTimeMillis(), "Genesis Block", "0")
  println(result)
  }

  /*case class AddBlock(newBlock: Block) {

    newBlock.previousHash =
  }
*/
}



