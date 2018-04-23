
public class PlayerCanNotPlay extends PlayerState {

	public PlayerCanNotPlay(Player player) {
		super(player);
	}

	@Override
	public void move() {
		System.out.println("Not your turn.");
	}

	@Override
	public void switchTurn() {
		player.setState(new PlayerCanPlay(player));
	}
}
