
public class PlayerCanNotPlay extends PlayerState {

	public PlayerCanNotPlay(Player player) {
		super(player);
	}

	@Override
	public boolean isTurn() {
		System.out.println("Player2");
		return false;
	}

}
