
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
		if((x+point)%10!=0) {
			if(x+point>10)
				position=new Square(x+point%10, y+1);
			else 
				position=new Square(x+point%10, y);
		}else {
			position=new Square(x, y+1);
		}
	}
	
	public Square getPosition() {
		return position;
	}
}
