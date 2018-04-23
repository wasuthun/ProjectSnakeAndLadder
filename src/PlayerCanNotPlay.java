
public class PlayerCanNotPlay extends PlayerState {

	public PlayerCanNotPlay(Player player) {
		super(player);
	}

	public void move() {
		
	}

	@Override
	public void switchTurn() {
		player.setState(new PlayerCanPlay(player));
	}
}
