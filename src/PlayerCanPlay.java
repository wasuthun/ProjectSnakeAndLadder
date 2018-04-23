
public class PlayerCanPlay extends PlayerState {
	private Square position;
	private Dice dice = new Dice();
	private int x;
	private int y;

	public PlayerCanPlay(Player player) {
		super(player);
	}

	public void move() {
		dice.roll();
		int point = dice.getPoint();
		if ((x + point) % 9 != 0) {
			if (x + point > 9 && y % 2 == 0 && x + point < 20)
				position = new Square(9 - ((x + point) - 10), y + 1);
			else if ((x + point) > 9 && y % 2 == 1 && x + point < 20)
				if (9 - (x - point + 10) > 9)
					position = new Square(10 - (x + point) % 10, y + 2);
				else
					position = new Square(9 - (x - point + 10), y + 1);
			else if (point + x > 20) {
				if (y % 2 == 0)
					position = new Square(9 - ((x + point) % 20), y + 2);
			} else
				if(y%2==0)
					position = new Square(x + point % 9, y);
				else
					position = new Square(x - point % 9, y);
		} else {
			position = new Square(x, y + 1);
		}
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

	@Override
	public void switchTurn() {
		player.setState(new PlayerCanNotPlay(player));
	}
}
