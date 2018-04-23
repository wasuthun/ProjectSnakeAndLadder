
public class PlayerCanNotPlay implements PlayerStage {
	private Square position;
	private int x;
	private int y;

	public PlayerCanNotPlay() {
		position = new Square(0, 0);
		this.x = 0;
		this.y = 0;
	}

	public void move() {

	}
	public void setPosition(Square square) {
		this.position=square;
	}
	public Square getPosition() {
		return position;
	}

	@Override
	public Dice getDice() {
		return null;
	}
	
}
