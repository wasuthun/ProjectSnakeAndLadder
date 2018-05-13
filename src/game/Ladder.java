package game;

/**
 * This class contain position of ladder.
 * @author Wasuthan, Patcharapol
 *
 */
public class Ladder {
	private Square top;
	private Square bottom;
	
	public Ladder(Square top , Square bottom) {
		this.top = top;
		this.bottom = bottom;
	}
	
	/**
	 * @return Square which is a top of ladder.
	 */
	public Square getTop() {
		return top;
	}
	
	/**
	 * @return Square which is a bottom of ladder.
	 */
	public Square getBottom() {
		return bottom;
	}
}