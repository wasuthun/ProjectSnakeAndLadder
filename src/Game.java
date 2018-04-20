
public class Game {
	private static Game game=new Game();
	private Player player1,player2;
	private Game() {
		player1=new Player();
		player2=new Player();
	}
	public static Game getInstance() {
		return game;	
	} 
	public boolean isEnd() {
		return (player1.getPosition().getX()==0&&player1.getPosition().getY()==10)||(player2.getPosition().getX()==0&&player2.getPosition().getY()==10);
	}
	
	
}
