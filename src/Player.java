
public class Player {
	private Square position;
	private Dice dice=new Dice();
	private int x;
	private int y;
	public Player() {
		position=new Square(0, 0);
		this.x=0;
		this.y=0;
	}
	public void move(){
		dice.roll();
		int point = dice.getPoint();
		position.setX(x+point);		
	}
	
	public Square getPosition() {
		return position;
	}
}
