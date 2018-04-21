import java.util.ArrayList;
import java.util.List;



public class Board {
	private int size = 100;
    private List<Square> blocks = new ArrayList<Square>();
	
	 public int getSize() {
	        return size;
	    }

	    public List<Square> getBlocks() {
	        return blocks;
	    }

	    public void addBlock(Square block) {
	        blocks.add(block);
	    }
	
}
