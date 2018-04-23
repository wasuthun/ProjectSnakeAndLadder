
public class Player {
	private PlayerState state;
	private Dice dice = new Dice();
	private Square position;
	
	public Player() {
		this.state = new PlayerCanNotPlay(this);
		position = new Square(0, 0);
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
	
	public void move() {
		state.move();
		state.switchTurn();
	}
	
	public void setPosition(Square square) {
		this.position=square;
	}
	
	public Square getPosition() {
		return position;
	}
	
	public Dice getDice(){
		return dice;
	}
}
