import spray.json.{DefaultJsonProtocol, DeserializationException, JsArray, JsNumber, JsObject, JsString, JsValue, RootJsonFormat, enrichAny}

/**
 * @author Santosh
 */

/**
 * Reading/ Writing of Block data from/ to Json
 */

object JsonProtocol extends DefaultJsonProtocol {

  implicit object BlockJsonFormat extends RootJsonFormat[Block] {
    def write(b: Block): JsObject = JsObject(
      "timestamp" -> JsNumber(b.timestamp),
      "transaction" -> JsObject(b.transaction),
      "previousHash" -> JsString(b.previousHash),
      "hash" -> JsString(b.hash)
    )

    def read(value: JsValue): Block = {
      value.asJsObject.getFields("timestamp", "transaction", "previousHash", "hash") match {
        case Seq(JsNumber(timestamp), JsString(transaction), JsString(previousHash), JsString(hash)) =>
           Block(timestamp.toLong, transaction.convertTo[List[Transaction]], previousHash, hash)
        case _ => throw DeserializationException("Block expected")
      }
    }
  }

  implicit object ChainLinkJsonFormat extends RootJsonFormat[ChainLink] {
    override def read(json: JsValue): ChainLink = json.asJsObject.getFields("index", "proof", "values", "previousHash", "timestamp", "tail") match {
      case Seq(JsNumber(index), JsNumber(proof), values, JsString(previousHash), JsNumber(timestamp), tail) =>
        ChainLink(index.toInt, proof.toLong, values.convertTo[List[Transaction]], previousHash, timestamp.toLong, tail.convertTo(ChainJsonFormat))
      case _ => throw new DeserializationException("Cannot deserialize: Chainlink expected")
    }

    override def write(obj: ChainLink): JsValue = JsObject(
      "index" -> JsNumber(obj.index),
      "proof" -> JsNumber(obj.proof),
      "values" -> JsArray(obj.values.map(_.toJson).toVector),
      "previousHash" -> JsString(obj.previousHash),
      "timestamp" -> JsNumber(obj.timestamp),
      "tail" -> ChainJsonFormat.write(obj.tail)
    )
  }

}
