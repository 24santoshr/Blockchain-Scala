import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

/**
 * @author Santosh
 */

/*
object JsonProtocol extends DefaultJsonProtocol {

  implicit object BlockJsonFormat extends RootJsonFormat[Block] {
    def write(t: Block) = JsObject(
      "index" -> JsNumber(t.index),
      "timestamp" -> JsNumber(t.timestamp),
      "data" -> JsString(t.data),
      "previousHash" -> JsString(t.previousHash)
    )

    def read(value: JsValue) = {
      value.asJsObject.getFields("index", "timestamp", "data", "previousHash") match {
        case Seq(JsNumber(index), JsNumber(timestamp), JsString(data), JsString(previousHash)) =>
          new Block(index.toInt, timestamp.toLong, data, previousHash)
        case _ => throw new DeserializationException("Block expected")
      }
    }

  }

}
*/