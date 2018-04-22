

public class Main {
	  public static void main(String[] args) {
	       	Game game = Game.getInstance();
	        GameUI gui = new GameUI(game);
//	        gui.setVisible(true);
	        game.start();
	    }
}
