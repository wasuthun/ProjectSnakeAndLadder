package player;

public class PlayerCanPlay extends PlayerState {
	private boolean turn = true;
	public PlayerCanPlay(Player player) {
		super(player);
	}

	@Override
	public boolean isTurn() {
		return turn;
	}
}
