import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerUIController {
	@FXML
	private Button roll;
	@FXML
	private Label point;
	@FXML
	private Label turn;
	@FXML
	private ProgressBar time;
	@FXML
	private TextField textField1;
	@FXML
	private TextField textField2;
	@FXML
	private ImageView face;
	private Game game = Game.getInstance();
	private Player player;

	@FXML

	public void initialize() {
		roll.setOnAction(this::handleRoll);
	}

	@FXML
	public void handleRoll(ActionEvent event) {
		player = game.getPlayer();
		textField2.setText("player " + (game.getPlayerList().indexOf(player) + 1));
		game.getDice().roll();
		int point = game.getDice().getPoint();
		// player.move(12);
		// System.out.println(player.getPosition().getX());
		// System.out.println(player.getPosition().getY());
		// player.move(7);
		textField1.setText("" + point);
		setFace(point);
		// System.out.println(player.getPosition().getX());
		// System.out.println(player.getPosition().getY());
		// player.move(12);
		// System.out.println(player.getPosition().getX());
		// System.out.println(player.getPosition().getY());
		player.move(point);
		System.out.println(player.getPosition().getX());
		System.out.println(player.getPosition().getY());
		game.switchTurn();
	}

	private void setFace(int point) {
		if (point == 1)
			face.setImage(new Image("Image/1.png"));
		else if(point == 2)
			face.setImage(new Image("Image/2.png"));
		else if(point == 3)
			face.setImage(new Image("Image/3.png"));
		else if(point == 4)
			face.setImage(new Image("Image/4.png"));
		else if(point == 5)
			face.setImage(new Image("Image/5.png"));
		else if(point == 6)
			face.setImage(new Image("Image/6.png"));
	}
}
