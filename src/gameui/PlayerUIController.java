package gameui;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.Player;

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
	private TextField textField3;
	@FXML
	private ImageView face1;
	@FXML
	private ImageView face2;
	private Game game = Game.getInstance();
	private Player player;
	private List<Integer> replay = new ArrayList<Integer>();

	@FXML
	public void initialize() {
		roll.setOnAction(this::handleRoll);
	}

	@FXML
	public void handleRoll(ActionEvent event) {
		player = game.getPlayer();
		textField3.setText("player " + (game.getPlayerList().indexOf(player) + 1));
		game.getDice().roll();
		int firstDie = game.getDice().getFirstDie();
		int secondDie = game.getDice().getSecondDie();
		int point = game.getDice().getPoint();
		textField1.setText("" + firstDie);
		textField2.setText("" + secondDie);
		setFace(face1, firstDie);
		setFace(face2, secondDie);
		try {
			if ((player.getPosition().getY() < 0)
					|| (player.getPosition().getX() == 0 && player.getPosition().getY() == 0)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Your win!!");
				alert.showAndWait();
			}

			player.move(point);
		} catch (NullPointerException e) {
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			face1.setImage(null);
			face2.setImage(null);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Please add player");
			alert.showAndWait();
		}
		if (game.isOver() == false)
			replay.add(point);
		game.replay = replay;
		System.out.println(game.replay.toString());
		game.switchTurn();
	}

	private void setFace(ImageView face, int point) {
		if (point == 1)
			face.setImage(new Image("Image/1.png"));
		else if (point == 2)
			face.setImage(new Image("Image/2.png"));
		else if (point == 3)
			face.setImage(new Image("Image/3.png"));
		else if (point == 4)
			face.setImage(new Image("Image/4.png"));
		else if (point == 5)
			face.setImage(new Image("Image/5.png"));
		else if (point == 6)
			face.setImage(new Image("Image/6.png"));
	}

}
