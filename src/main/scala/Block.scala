/**
 * @author Santosh
 */

/**
  model for a Block
 * @param index: denotes index of the block
 * @param timestamp: denotes timestamp of the block
 * @param data: denotes transactional data of the block
 * @param previousHash: benotes previous SHA256 value of the block
 * @param hash: denotes current SHA256 value of the block
 */
case class Block(index: Int, timestamp: Long = System.currentTimeMillis(), data: String, previousHash: String, hash: String)