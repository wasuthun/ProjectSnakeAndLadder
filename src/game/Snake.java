package game;

/**
 * This class contain positon of snake.
 * @author mark
 *
 */
public class Snake {

	private Square head;
	private Square tail;
  
	public Snake(Square head,Square tail) {
		this.head = head;
		this.tail = tail;
	}
	
	/**
	 * @return Square which is a head of snake.
	 */
	public Square getHead() {
		return head;
	}
	
	/**
	 * @return Square which is a tail of snake.
	 */
	public Square getTail() {
		return tail;
	}

}

