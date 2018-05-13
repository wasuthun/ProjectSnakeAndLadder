package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Board class this use to collect a list of position of player
 * 
 * @author Wasuthun and Patcharapol
 *
 */
public class Board {
	private List<Square> square = new ArrayList<Square>();

	/**
	 * Get list of position player
	 * 
	 * @return list of position of player
	 */
	public List<Square> getSquares() {
		return square;
	}

}
