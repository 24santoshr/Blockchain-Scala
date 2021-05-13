import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

/**
 * @author Santosh
 */

/**
 * Reading/ Writing of Block data from/ to Json
 */

object JsonProtocol extends DefaultJsonProtocol {

  implicit object BlockJsonFormat extends RootJsonFormat[Block] {
    def write(b: Block): JsObject = JsObject(
      "index" -> JsNumber(b.index),
      "timestamp" -> JsNumber(b.timestamp),
      "data" -> JsString(b.data),
      "previousHash" -> JsString(b.previousHash),
      "hash" -> JsString(b.hash)
    )

    def read(value: JsValue): Block = {
      value.asJsObject.getFields("index", "timestamp", "data", "previousHash", "hash") match {
        case Seq(JsNumber(index), JsNumber(timestamp), JsString(data), JsString(previousHash), JsString(hash)) =>
           Block(index.toInt, timestamp.toLong, data, previousHash, hash)
        case _ => throw  DeserializationException("Block expected")
      }
    }

  }

}
