
public class Game {
	private static Game game=new Game();
	private Player player1,player2;
	//
	private Game() {
		player1=new Player();
		player2=new Player();
	}
	public static Game getInstance() {
		return game;	
	} 
	public boolean isEnd() {
		return player1.getCollect()==100||player2.getCollect()==100;
	}
	
}
