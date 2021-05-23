import spray.json._

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
      "transaction" -> JsArray(b.transaction.map(_.toJson).toVector),
      "previousHash" -> JsString(b.previousHash),
      "hash" -> JsString(b.hash)
    )

    def read(value: JsValue): Block = {
      value.asJsObject.getFields("timestamp", "transaction", "previousHash", "hash") match {
        case Seq(JsNumber(timestamp), transaction, JsString(previousHash), JsString(hash)) =>
          Block(timestamp.toLong, transaction.convertTo[List[Transaction]], previousHash, hash)
        case _ => throw DeserializationException("Block expected")
      }
    }
  }


  implicit object TransactionJsonFormat extends RootJsonFormat[Transaction] {
    def write(t: Transaction) = JsObject(
      "fromAddress" -> JsString(t.fromAddress),
      "toAddress" -> JsString(t.toAddress),
      "amount" -> JsNumber(t.amount),
    )

    def read(value: JsValue): Transaction = {
      value.asJsObject.getFields("fromAddress", "toAddress", "amount") match {
        case Seq(JsString(fromAddress), JsString(toAddress), JsNumber(amount)) =>
          new Transaction(fromAddress, toAddress, amount.toLong)
        case _ => throw new DeserializationException("Transaction expected")
      }
    }
  }

}
