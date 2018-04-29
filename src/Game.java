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
	private boolean running=true;
	private Thread gameThread = new Thread() {
	        @Override
	        public void run() {
	            super.run();
	            while(running) {
	                oneGameLoop();
	            }
	        }
	    };

	private Game() {
		board = new Board();
		//snake
		//ladder
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
		if(players.size() == 0) {
			players.add(player);
			currentPlayer = players.get(0);
			return true;
		}
		else if (players.size() < 4) {
			players.add(player);
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
		running=true;
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
//			if (turn % players.size() == 1) {
//				players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
//			} else {
//				currentPlayer = players.get(i);
//				System.out.println("switch");
//				players.get(i).setState(new PlayerCanPlay(players.get(i)));
//			}
			if (turn % players.size() == i) {
				System.out.println("player "+(i+1));
				currentPlayer = players.get(i);
				players.get(i).setState(new PlayerCanPlay(players.get(i)));
			} else {
				System.out.println("switch");
				players.get(i).setState(new PlayerCanNotPlay(players.get(i)));
			}
		}
		System.out.println("turn"+turn);
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
	
	public Dice getDice() {
		return dice;
	}
	public List<Player> getPlayerList(){
		return players;
	}
}
