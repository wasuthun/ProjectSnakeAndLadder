
public abstract class PlayerState {
	protected Player player;
	
	public PlayerState(Player player) {
		this.player = player;
	}
	public abstract void move();
	public abstract void switchTurn() ;
}
