
public class Player {
	private Square position;
	private Dice dice = new Dice();
	private int x;
	private int y;

	public Player() {
		position = new Square(0, 0);
		this.x = 0;
		this.y = 0;
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

	public Square getPosition() {
		return position;
	}
}
