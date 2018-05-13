package game;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Observable;
import javax.swing.JOptionPane;

import player.Player;
import player.PlayerCanNotPlay;
import player.PlayerCanPlay;

/**
 * This class is a Game of this project and contain many logic of this game to
 * perform in GameUI
 * 
 * @author Wasuthun and Patcharapol
 *
 */
public class Game extends Observable {
	private static Game game = new Game();
	private List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private Dice dice = new Dice();
	private int turn = 1;
	private boolean running = true;
	private List<Square> playerPos = new ArrayList<>();
	public List<Integer> replay = new ArrayList<Integer>();
	public List<Integer> replaytmp = new ArrayList<Integer>();
	private List<Ladder> ladder = new ArrayList<Ladder>();
	private List<Snake> snake = new ArrayList<Snake>();
	private List<BackwardSquare> backlist = new ArrayList<BackwardSquare>();
	private List<FreezeSquare> freezelist = new ArrayList<FreezeSquare>();
	private Thread gameThread = new Thread() {
		@Override
		public void run() {
			super.run();
			while (running) {
				oneGameLoop();
			}
		}
	};
	private Thread replayThread = new Thread() {
		@Override
		public void run() {
			super.run();
			game.restart();
			for (int point : replay) {
				currentPlayer.move(point);
				try {
					replayThread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.gamelogic();
				game.switchTurn();
				updateBoard();
			}
			if (replayThread.isAlive()) {
				JOptionPane.showMessageDialog(null, "Replay is End");
				replayThread.stop();
			}
			replay.clear();
		}
	};

	/**
	 * Constructor
	 */
	private Game() {
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

		backlist.add(new BackwardSquare(3, 0));
		backlist.add(new BackwardSquare(4, 2));
		backlist.add(new BackwardSquare(2, 8));

		freezelist.add(new FreezeSquare(7, 0));
		freezelist.add(new FreezeSquare(2, 4));
		freezelist.add(new FreezeSquare(6, 7));
		freezelist.add(new FreezeSquare(3, 9));
		updateBoard();
	}

	/**
	 * One loop of this game that use to run in game thread
	 */
	private void oneGameLoop() {
		gamelogic();
		setChanged();
		notifyObservers();
		delay();
	}

	/**
	 * Use to delay a game thread
	 */
	private void delay() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method use to perform action when player on a snake tail and bottom of
	 * ladder and end point of this game
	 */
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
			if ((player.getPosition().getX() == 0 && player.getPosition().getY() == 0)) {
				this.end();
			}
		}
		updateBoard();
	}

	/**
	 * Use to update a position of player
	 */
	public void updateBoard() {
		playerPos.clear();
		for (Player player : players) {
			playerPos.add(player.getPosition());
		}
	}

	/**
	 * Get a list of board that contain player position
	 * 
	 * @return
	 */
	public List<Square> getSquares() {
		return playerPos;
	}

	/**
	 * Use to add player in the game
	 * 
	 * @param player
	 * @return boolean that show a player is add or not
	 */
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

	/**
	 * Get a game singleton object
	 * 
	 * @return game object
	 */
	public static Game getInstance() {
		return game;
	}

	/**
	 * Start of this game
	 */
	public void start() {
		running = true;
		gameThread.start();
	}

	/**
	 * End of this game
	 */
	public void end() {
		running = false;
	}

	/**
	 * Check a game is end or not
	 * 
	 * @return result of checking
	 */
	public boolean isOver() {
		return !running;
	}

	/**
	 * Use to switch turn of player in player list after player roll dice
	 */
	public void switchTurn() {
		for (int i = 0; i < players.size(); i++) {
			if (turn % players.size() == i) {
				currentPlayer = players.get(i);
				players.get(i).setState(new PlayerCanPlay(players.get(i)));
			} else {
				players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
			}
		}
		turn++;
	}

	/**
	 * Access a current player
	 * 
	 * @return current player
	 */
	public Player getPlayer() {
		return currentPlayer;
	}

	/**
	 * Use to restart a game
	 */
	public void restart() {
		try {
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
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please add player");
		}
	}

	/**
	 * Access a dice
	 * 
	 * @return dice object
	 */
	public Dice getDice() {
		return dice;
	}

	/**
	 * Access a player list
	 * 
	 * @return list of player
	 */
	public List<Player> getPlayerList() {
		return players;
	}

	/**
	 * Access a BackwordSquare list
	 * 
	 * @return backword square list
	 */
	public List<BackwardSquare> getBacklist() {
		return backlist;
	}

	/**
	 * Get replay of this game
	 * 
	 * @throws InterruptedException
	 */
	public void getReplay() throws InterruptedException {
		if (!replayThread.isAlive()) {
			replayThread = new Thread() {
				@Override
				public void run() {
					super.run();
					game.restart();
					Integer[] replayAarr = new Integer[replay.size()];
					replay.toArray(replayAarr);
					try {
						for (int point : replay) {
							currentPlayer.move(point);
							try {
								replayThread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							game.gamelogic();
							game.switchTurn();
							updateBoard();

						}
					} catch (ConcurrentModificationException e) {
						JOptionPane.showMessageDialog(null, "Dont press restart and roll dice during replay");
						game.restart();
					}
					if (replayThread.isAlive()) {
						JOptionPane.showMessageDialog(null, "Replay is end please restart or exit program");
						replayThread.stop();
					}
					replay.clear();
				}
			};
		}
		if (!replayThread.isAlive())
			replayThread.start();
	}

	/**
	 * Get list of point dice of replay
	 * 
	 * @return get point of dice list
	 */
	public List<Integer> getPointListReplay() {
		return replay;
	}

	/**
	 * Access a FreezeSquarList
	 * 
	 * @return Use to Access a FreezeSquare List
	 */
	public List<FreezeSquare> getFreezelist() {
		return freezelist;
	}

	/**
	 * Save a state as Memento object
	 * 
	 * @return Memento save object
	 */
	public Memento save() {
		return new Memento(players, turn);
	}

	/**
	 * Use to load state of object from save state
	 * 
	 * @param m
	 */
	public void load(Memento m) {
		try {
			int index = players.indexOf(m.currentPlayerM);
			players.clear();
			for (Player p : m.player) {
				Player p1 = new Player();
				p1.setPosition(new Square(p.getPosition().getX(), p.getPosition().getY()));
				this.players.add(p1);
			}
			this.currentPlayer = players.get(index);
			m.currentPlayerM = this.currentPlayer;
			this.turn = m.turn;
			currentPlayer.setState(new PlayerCanPlay(currentPlayer));
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Please save first");
		}
	}

	/**
	 * Inner class Memento that use to write a Memento pattern to save and load a
	 * program
	 * 
	 * @author Wasuthun and Patcharapol
	 *
	 */
	public static class Memento {
		private List<Player> player = new ArrayList<Player>();
		private int turn;
		private Game game = Game.getInstance();
		private Player currentPlayerM;

		/**
		 * Constructor
		 * 
		 * @param player
		 * @param turn
		 */
		public Memento(List<Player> player, int turn) {
			for (Player p : player) {
				Player p1 = new Player();
				p1.setPosition(new Square(p.getPosition().getX(), p.getPosition().getY()));
				this.player.add(p1);
			}
			this.turn = turn;
			currentPlayerM = game.getPlayer();
		}
	}

}
