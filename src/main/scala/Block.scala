/**
 * @author Santosh
 */


import spray.json.{DefaultJsonProtocol, JsonFormat}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

case class Block(index: Int, timestamp: Long = System.currentTimeMillis(), data: String, previousHash: String = "")