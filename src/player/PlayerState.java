package player;

public abstract class PlayerState {
	protected Player player;
	
	public PlayerState(Player player) {
		this.player = player;
	}
	public abstract boolean isTurn();
}
