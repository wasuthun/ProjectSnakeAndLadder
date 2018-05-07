package game;
import java.util.Random;

public class Dice {
		private int firstDie=0;
		private int secondDie=0;

	public void roll() {
		Random rand=new Random();
		firstDie=rand.nextInt(6)+1;
		secondDie=rand.nextInt(6)+1;
	}
	public int getFirstDie() {
		return firstDie;
	}
	public int getSecondDie() {
		return secondDie;
	}
	public int getPoint() {
		return secondDie+firstDie;
	}
}
