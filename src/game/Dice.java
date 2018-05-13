package game;

import java.util.Random;

/**
 * A Dice class which contain 2 dices.
 * @author Wasuthan, Patcharapol
 *
 */
public class Dice {
	private int firstDie = 0;
	private int secondDie = 0;

	/**
	 * Roll 2 dices at the same time.
	 */
	public void roll() {
		Random rand = new Random();
		firstDie = rand.nextInt(6) + 1;
		secondDie = rand.nextInt(6) + 1;

	}

	/**
	 * @return points of first dice.
	 */
	public int getFirstDie() {
		return firstDie;
	}

	/**
	 * @return points of second dice.
	 */
	public int getSecondDie() {
		return secondDie;
	}

	/**
	 * @return points of two dices.
	 */
	public int getPoint() {
		return secondDie + firstDie;
	}
}
