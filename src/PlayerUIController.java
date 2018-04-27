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
	private ImageView imageV1;
	@FXML
	private Image image; 
	
	private Player player1=new Player();
	@FXML
	
	public void initialize() {
		roll.setOnAction(this::handleRoll);
	}
	@FXML
	public void handleRoll(ActionEvent event) {
		player1.getDice().roll();
		textField1.setText(""+player1.getDice().getFirstDie());
		textField2.setText(""+player1.getDice().getSecondDie());
//		if(player1.getDice().getFirstDie()==1) {
//			image = new Image("/face1.jpg");
//		} 
//		
//		imageV1=new ImageView();
//		imageV1.setImage(image);
	}
}
