package game;

/**
 * Square is a block in game board. 
 * @author Wasuthan, Patcharapol
 *
 */
public class Square {
	private int x;
	private int y;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return position of this square in x axis.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return position of this square in y axis.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set position in x axis.
	 * @param x is new position in x axis.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set position in y axis.
	 * @param y is new position in y axis.
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
}
