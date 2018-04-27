
public class PlayerCanPlay extends PlayerState {
	private boolean turn = true;
	public PlayerCanPlay(Player player) {
		super(player);
	}

	@Override
	public boolean isTurn() {
		System.out.println("player1");
		return turn;
	}
}
