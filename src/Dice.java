import java.util.Random;

public class Dice {
	private int point = 0;

	public void roll() {
		Random rand = new Random();
		point = rand.nextInt(6) + 1;
	}

	public int getPoint() {
		return point;
	}
}
