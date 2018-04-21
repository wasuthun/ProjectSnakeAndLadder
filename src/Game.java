import java.util.Observable;

public class Game extends Observable{
	private static Game game=new Game();
	private Player player1,player2;
	private Board board;
	//create snake and ladder
	private Game() {
		player1=new Player();
		player2=new Player();
		board=new Board();
	}
	public static Game getInstance() {
		return game;	
	} 
	public boolean isEnd() {
		return (player1.getPosition().getX()==0&&player1.getPosition().getY()==10)||(player2.getPosition().getX()==0&&player2.getPosition().getY()==10);
	}
	
}
