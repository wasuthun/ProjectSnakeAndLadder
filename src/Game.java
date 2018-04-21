import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {
	private static Game game = new Game();
	private List<Player> players;
	private Board board;

	// create snake and ladder
	private Game() {
		players = new ArrayList<Player>();
		board = new Board();
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
		for (Player p : players) {
			if (p.getPosition().getX() == 9 && p.getPosition().getY() == 9)
				return true;
			else if (p.getPosition().getY() > 9)
				return true;
		}
		return false;
	}
	
	public void start() {
		
	}
}
