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
	private List<Ladder> ladder = new ArrayList<Ladder>();
	private List<Snake> snake = new ArrayList<Snake>();
	private boolean freeze = true;
	private int count = 0;
	private FreezeSquare fs;
	private Thread gameThread = new Thread() {
		@Override
		public void run() {
			super.run();
			while (running) {
				oneGameLoop();
			}
		}
	};

	private Game() {
		board = new Board();
		snake=new ArrayList<>();
		ladder=new ArrayList<>();
		snake.add(new Snake(new Square(5,0), new Square(2, 6)));
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
		ladder.add(new Ladder(new Square(5, 3), new Square(4, 0)));
		ladder.add(new Ladder(new Square(9, 5), new Square(8, 0)));
		fs = new FreezeSquare(2, 2);
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
					System.out.println("snake!!");
					player.setPosition(snake.getTail());
				}
			}
			
			for (Ladder ladder : ladder) {
				for (Player player : players) {
					if (player.getPosition().getX() == ladder.getBottom().getX()
							&& player.getPosition().getY() == ladder.getBottom().getY()) {
						System.out.println("ladder!!");
						player.setPosition(ladder.getTop());
					}
				}
			}

			for (Player player : players) {
				if (player.getPosition().getX() == fs.getX() && player.getPosition().getY() == fs.getY()) {
					if (freeze) {
						count = turn;
						freeze = false;
						// System.out.println("============");
						// System.out.println("cccccc "+count);
					}
					player.setState(new PlayerCanNotPlay(player));
					if (count + 2 * players.size() - 1 == turn) {
						// System.out.println("ss");
						player.setState(new PlayerCanPlay(player));
					}
				}
				if ((player.getPosition().getY() > 9)
						|| (player.getPosition().getX() == 0 && player.getPosition().getY() == 9)) {
					this.end();
				}
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
		} else if (players.size() < 4) {
			players.add(player);
			return true;
		}
		return false;
	}

	public static Game getInstance() {
		return game;
	}

	// public boolean isEnd() {
	// // for (Player p : players) {
	// // if (p.getPosition().getX() == 9 && p.getPosition().getY() == 9)
	// // return true;
	// // else if (p.getPosition().getY() > 9)
	// // return true;
	// // }
	//// if (player1.getPosition().getX() == 9 && player1.getPosition().getY() == 9)
	//// return true;
	//// else if (player1.getPosition().getY() > 9)
	//// return true;
	//// return false;
	// }

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
			// if (turn % players.size() == 1) {
			// players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
			// } else {
			// currentPlayer = players.get(i);
			// System.out.println("switch");
			// players.get(i).setState(new PlayerCanPlay(players.get(i)));
			// }
			if (turn % players.size() == i) {
				System.out.println("player " + (i + 1));
				currentPlayer = players.get(i);
				players.get(i).setState(new PlayerCanPlay(players.get(i)));
			} else {
				// System.out.println("switch");
				players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
			}
		}
		System.out.println("turn" + turn);
		turn++;
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public void restart() {
		turn = 1;
		count = 0;
		freeze = true;
		for (Player player : players) {
			player.setPosition(new Square(0, 0));
		}
		running = true;
		if (!gameThread.isAlive()) {
			new Thread(gameThread).start();
		}
	}

	public Dice getDice() {
		return dice;
	}

	public List<Player> getPlayerList() {
		return players;
	}

}
