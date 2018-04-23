import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

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
	private TextArea textArea1;
	@FXML
	private TextArea textArea2;
	private Game game;
	@FXML
	public void initialize() {
		game=Game.getInstance();
		roll.setOnAction(this::handleRoll);
	}
	@FXML
	public void handleRoll(ActionEvent event) {
//		textArea1.setText(""+player.getDice().getFirstDie());
//		textArea2.setText(""+player.getDice().getSecondDie());
		
	}
}
