package player;

/**
 * Player can not play state.
 * @author Wasuthan, Patcharapol
 *
 */
public class PlayerCanNotPlay extends PlayerState {

	public PlayerCanNotPlay(Player player) {
		super(player);
	}

	/**
	 * @return false mean it's not player turn.
	 */
	@Override
	public boolean isTurn() {
		return false;
	}

}
