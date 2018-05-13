package player;

/**
 * Player can play state.
 * @author Wasuthan, Patcharapol
 *
 */
public class PlayerCanPlay extends PlayerState {

	public PlayerCanPlay(Player player) {
		super(player);
	}

	/**
	 * @return true mean it's this player turn.
	 */
	@Override
	public boolean isTurn() {
		return true;
	}
}
