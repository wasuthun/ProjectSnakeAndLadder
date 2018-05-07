package player;

public class PlayerCanNotPlay extends PlayerState {

	public PlayerCanNotPlay(Player player) {
		super(player);
	}

	@Override
	public boolean isTurn() {
		return false;
	}

}
