import spray.json._

/**
 * @author Santosh
 */

/**
 * Reading/ Writing of Block data from/ to Json
 */

object JsonProtocol extends DefaultJsonProtocol {

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


  implicit object BlockJsonFormat extends RootJsonFormat[Block] {
    def write(b: Block): JsObject = JsObject(
      "timestamp" -> JsNumber(b.timestamp),
      "transaction" -> JsArray(b.transaction.map(_.toJson).toVector),
      "previousHash" -> JsString(b.previousHash)
      //"currentHash" -> JsString(b.currentHash)
    )

    def read(value: JsValue): Block = {
      value.asJsObject.getFields("timestamp", "transaction", "previousHash") match {
        case Seq(JsNumber(timestamp), transaction, JsString(previousHash)) =>
          Block(timestamp.toLong, transaction.convertTo[List[Transaction]], previousHash)
        case _ => throw DeserializationException("Block expected")
      }
    }
  }



  /*
    implicit object ChainLinkJsonFormat extends RootJsonFormat[ChainLink] {
      def write(c: ChainLink) = JsObject(
        "nonce" -> JsNumber(c.nonce),
        "transaction" -> JsArray(c.transaction.map(_.toJson).toVector),
        "previousHash" -> JsString(c.previousHash),
        "timestamp" -> JsNumber(c.timestamp),
        "tail" ->ChainJsonFormat.write(c.tail)
      )

      def read(value: JsValue): ChainLink = {
        value.asJsObject.getFields("nonce", "transaction", "previousHash", "timestamp", "tail") match {
          case Seq(JsNumber(nonce), transaction,JsString(previousHash), JsNumber(timestamp), tail) =>
            new ChainLink(nonce.toInt, transaction.convertTo[List[Transaction]],previousHash, timestamp.toLong, tail.convertTo(ChainJsonFormat))
          case _ => throw new DeserializationException("ChainLink expected")
        }
      }
    }

    implicit object ChainJsonFormat extends RootJsonFormat[BlockTrait] {
      def write(obj: BlockTrait): JsValue = obj match {
        case link: ChainLink => link.toJson
        case FirstBlock => JsObject(
          "nonce" -> JsNumber(FirstBlock.nonce),
          "hash" -> JsString(FirstBlock.hash),
          "transaction" -> JsArray(),
          "timeStamp" -> JsNumber(FirstBlock.timestamp)
        )
      }

      def read(json: JsValue): BlockTrait = {
        json.asJsObject.getFields("previousHash") match {
          case Seq(_) => json.convertTo[ChainLink]
          case Seq() => FirstBlock
        }
      }
    }
  */
}
