import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {
	private static Game game = new Game();
	private List<Player> players;
	private Player player1,currentPlayer;
	private Board board;
	private int turn=0;

	private Game() {
		player1 = new Player();
		board = new Board();
	}
	
	public int getBoardSize() {
		return board.getSize();
	}
	
	public List<Square> getSquares(){
		return board.getSquares();
	}
	
	public boolean addPlayer(Player player) {
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
//		for (Player p : players) {
//			if (p.getPosition().getX() == 9 && p.getPosition().getY() == 9)
//				return true;
//			else if (p.getPosition().getY() > 9)
//				return true;
//		}
		if (player1.getPosition().getX() == 9 && player1.getPosition().getY() == 9)
			return true;
		else if (player1.getPosition().getY() > 9)
			return true;
		return false;
	}
	
	public void stopAtSnakeHead() {
		
	}
	
	public void start() {
		Square startPos =new Square(0, 0);
//		for (Player player : players) {
//			player.setPosition(startPos);
//		}
		player1.setPosition(startPos);
	}
	
	public void switchTurn(Player player) {
		if(turn%2==0) {
			player.setState(new PlayerCanNotPlay(player));
		}	
		else {
			currentPlayer=player;
			player.setState(new PlayerCanPlay(player));
		}
		System.out.println(turn);
		turn++;
	}
	public Player getPlayer() {
		return currentPlayer;
	}
	public void reset() {
		Square startPos=new Square(0, 0);
		for (Player player : players) {
			player.setPosition(startPos);
		}
	}
}
