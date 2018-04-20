import java.util.HashMap;

public class Board {
	int[][] board=new int[10][10];
	HashMap<Integer, Integer> map=new HashMap<>();
	public Board() {
		int k=0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j]=k++;
			}
		}
	}
	public void setSnake(){
		map.put(24, 3);
		map.put(52, 20);
	}
	public void setLadder() {
		map.put(26, 23);
		map.put(46, 72);
	}
	
}
