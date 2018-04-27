import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {
	private static Game game = new Game();
	private List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private Board board;
	private int turn = 0;

	private Game() {
		board = new Board();
	}

	public int getBoardSize() {
		return board.getSize();
	}

	public List<Square> getSquares() {
		return board.getSquares();
	}

	public boolean addPlayer(Player player) {
		if(players.size() == 0) {
			players.add(player);
			currentPlayer = players.get(0);
			System.out.println(currentPlayer.toString());
		}
		else if (players.size() < 4) {
			players.add(player);
			System.out.println("add");
			System.out.println(players.size());
			return true;
		}
		return false;
	}

	public static Game getInstance() {
		return game;
	}

//	public boolean isEnd() {
//		// for (Player p : players) {
//		// if (p.getPosition().getX() == 9 && p.getPosition().getY() == 9)
//		// return true;
//		// else if (p.getPosition().getY() > 9)
//		// return true;
//		// }
////		if (player1.getPosition().getX() == 9 && player1.getPosition().getY() == 9)
////			return true;
////		else if (player1.getPosition().getY() > 9)
////			return true;
////		return false;
//	}

	public void stopAtSnakeHead() {

	}

	public void start() {
		Square startPos = new Square(0, 0);
//		for (Player player : players) {
//			player.setPosition(startPos);
//		}

	}

	public void switchTurn() {
		for (int i = 0; i < players.size(); i++) {
			if (turn % players.size() == 0) {
				players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
			} else {
				currentPlayer = players.get(i);
				players.get(i).setState(new PlayerCanPlay(players.get(i)));
			}
			System.out.println(turn);
		}
		turn++;
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public void reset() {
		Square startPos = new Square(0, 0);
		for (Player player : players) {
			player.setPosition(startPos);
		}
	}
}
