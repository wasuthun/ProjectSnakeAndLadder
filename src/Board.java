import java.util.ArrayList;
import java.util.List;

public class Board {
	private int size = 10;
	private List<Square> square = new ArrayList<Square>();

	public int getSize() {
		return size;
	}

	public List<Square> getSquares() {
		return square;
	}

	public void addBlock(Square block) {
		square.add(block);
	}

}
