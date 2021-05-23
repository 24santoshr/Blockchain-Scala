/**
 * @author Santosh
 */

/**
  model for a Block
 * @param timestamp: denotes timestamp of the block
 * @param transaction: denotes transactional data of the block
 * @param previousHash: benotes previous SHA256 value of the block
 */
case class Block(timestamp: Long, transaction: List[Transaction], previousHash: String, hash: String)

