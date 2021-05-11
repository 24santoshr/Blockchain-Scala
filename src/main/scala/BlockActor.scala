/**
 * @author Santosh
 */

import BlockActor.{GenesisBlock, getHash}
import JsonProtocol.BlockJsonFormat
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol.StringJsonFormat
import spray.json.enrichAny
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success, Try}

/**
 * Actor System for creating/ managing Blocks
 */


class BlockActor extends Actor with ActorLogging {

  implicit val system: ActorSystem = ActorSystem("Blockchain")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher

  def receive: PartialFunction[Any, Unit] = {

    case GenesisBlock =>
      val index = 0
      val timestamp = System.currentTimeMillis()
      val data = "Genesis Block"
      val previousHash = "0"
      val currentHash = getHash(index, previousHash, timestamp, data)

      val genesisBlock = Try(Block(index, timestamp, data, previousHash, currentHash))

      genesisBlock match {
        case Success(value) => log.info(s"Genesis Block created with data ${value.toJson}")
        case Failure(ex) => log.warning(s"Exception with ${ex.getMessage}")
      }

    case x => log.warning("Received unknown message: [{}] ", x)

  }
}

object BlockActor {

  def props(): Props = Props(new BlockActor)

  case object GenesisBlock

  def getHash(index: Int, previousHash: String, timestamp: Long, data: String): String = {
    Crypto.sha256Hash(index + previousHash + timestamp + data.toJson)
  }
}



