import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {
	private static Game game = new Game();
	private List<PlayerState> players;
	private Board board;

	private Game() {
		players = new ArrayList<PlayerState>();
		board = new Board();
	}
	public int getBoardSize() {
		return board.getSize();
	}
	public List<Square> getSquares(){
		return board.getSquares();
	}
	public boolean addPlayer(PlayerState player) {
		if (players.size() < 4) {
			players.add(player);
			return true;
		}
		return false;
	}

	public static Game getInstance() {
		return game;
	}

	public boolean isEnd() {
		for (PlayerState p : players) {
			if (p.getPosition().getX() == 9 && p.getPosition().getY() == 9)
				return true;
			else if (p.getPosition().getY() > 9)
				return true;
		}
		return false;
	}
	public void stopAtSnakeHead() {
		
	}
	public void start() {
		Square startPos=new Square(0, 0);
		for (PlayerState player : players) {
			player.setPosition(startPos);
		}
		//do st
	}
	public void reset() {
		Square startPos=new Square(0, 0);
		for (PlayerState player : players) {
			player.setPosition(startPos);
		}
	}
}
