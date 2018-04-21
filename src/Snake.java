import java.util.List;

public class Snake {
	private Square head;
	private Square tail;
	
	public Snake(Square head,Square tail) {
		this.head = head;
		this.tail = tail;
	}
	
	public Square getHead() {
		return head;
	}
	
	public Square getTail() {
		return tail;
	}
}
