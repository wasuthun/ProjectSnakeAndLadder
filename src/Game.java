import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {
	private static Game game = new Game();
	private List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private Board board;
	private Dice dice = new Dice();
	private int turn = 1;
	private boolean running = true;
	public List<Integer> replay = new ArrayList<Integer>();
	public List<Integer> replaytmp = new ArrayList<Integer>();
	private List<Ladder> ladder = new ArrayList<Ladder>();
	private List<Snake> snake = new ArrayList<Snake>();
	private Thread gameThread=new Thread(){@Override public void run(){super.run();while(running){oneGameLoop();}}};

	private Game() {
		board = new Board();
		snake = new ArrayList<>();
		ladder = new ArrayList<>();
		snake.add(new Snake(new Square(5, 0), new Square(2, 6)));
		snake.add(new Snake(new Square(1, 1), new Square(0, 8)));
		snake.add(new Snake(new Square(6, 1), new Square(5, 3)));
		snake.add(new Snake(new Square(4, 4), new Square(8, 9)));
		snake.add(new Snake(new Square(8, 5), new Square(6, 9)));
		snake.add(new Snake(new Square(4, 6), new Square(4, 9)));
		// ladder
		ladder.add(new Ladder(new Square(9, 0), new Square(8, 3)));
		ladder.add(new Ladder(new Square(2, 1), new Square(1, 3)));
		ladder.add(new Ladder(new Square(5, 1), new Square(7, 5)));
		ladder.add(new Ladder(new Square(1, 5), new Square(2, 7)));
		ladder.add(new Ladder(new Square(5, 6), new Square(4, 9)));
		ladder.add(new Ladder(new Square(9, 4), new Square(8, 9)));
		updateBoard();
	}

	private void oneGameLoop() {
		gamelogic();
		setChanged();
		notifyObservers();
		delay();
	}

	private void delay() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void gamelogic() {
		for (Snake snake : snake) {
			for (Player player : players) {
				if (player.getPosition().getX() == snake.getHead().getX()
						&& player.getPosition().getY() == snake.getHead().getY()) {
					player.setPosition(new Square(snake.getTail().getX(), snake.getTail().getY()));
				}
			}
		}
		for (Ladder ladder : ladder) {
			for (Player player : players) {
				if (player.getPosition().getX() == ladder.getBottom().getX()
						&& player.getPosition().getY() == ladder.getBottom().getY()) {
					player.setPosition(new Square(ladder.getTop().getX(), ladder.getTop().getY()));
				}
			}
		}

		for (Player player : players) {
			if ((player.getPosition().getY() < 0)
					|| (player.getPosition().getX() == 0 && player.getPosition().getY() == 0)) {
				this.end();
			}
		}
		updateBoard();
	}

	public void updateBoard() {
		board.getSquares().clear();
		for (Player player : players) {
			board.getSquares().add(player.getPosition());
		}
	}

	public int getBoardSize() {
		return board.getSize();
	}

	public List<Square> getSquares() {
		return board.getSquares();
	}

	public boolean addPlayer(Player player) {
		if (players.size() == 0) {
			players.add(player);
			currentPlayer = players.get(0);
			return true;
		} else if (players.size() < 4 && players.size() > 0) {
			players.add(player);
			return true;
		}
		return false;
	}

	public static Game getInstance() {
		return game;
	}

	public void start() {
		running = true;
		gameThread.start();
	}

	public void end() {
		running = false;
	}

	public boolean isOver() {
		return !running;
	}

	public void switchTurn() {
		for (int i = 0; i < players.size(); i++) {
			if (turn % players.size() == i) {
				currentPlayer = players.get(i);
				players.get(i).setState(new PlayerCanPlay(players.get(i)));
			} else {
				// System.out.println("switch");
				players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
			}
		}
		turn++;
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public void restart() {
		currentPlayer = players.get(0);
		currentPlayer.setState(new PlayerCanPlay(currentPlayer));
		turn = 1;
		for (Player player : players) {
			player.setPosition(new Square(0, 9));
		}
		running = true;
		System.out.println(gameThread.isAlive());
		if (!gameThread.isAlive()) {
			gameThread = new Thread() {
				@Override
				public void run() {
					super.run();
					while (running) {
						oneGameLoop();
					}
				}
			};
			gameThread.start();
		}
	}

	public Dice getDice() {
		return dice;
	}

	public List<Player> getPlayerList() {
		return players;
	}

	public void getReplay() throws InterruptedException {
		game.restart();
		System.out.println(replay.toString());
		for (int point : replay) {
			currentPlayer.move(point);
			game.gamelogic();
			game.switchTurn();
		}
		replay.clear();
		updateBoard();
	}

	public List<Integer> getPointListReplay() {
		return replay;
	}

}
