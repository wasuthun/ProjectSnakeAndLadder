package player;

/**
 * Player state which have 2 states. First one is PlayerCanplay state another is PlayerCannotPlay state.
 * @author Wasuthan, Patcharapol
 *
 */
public abstract class PlayerState {
	protected Player player;
	
	public PlayerState(Player player) {
		this.player = player;
	}
	
	/**
	 * @return Is this player turn or not.
	 */
	public abstract boolean isTurn();
}
