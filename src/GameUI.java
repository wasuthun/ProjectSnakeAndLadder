import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI implements Observer {

	private JButton restartGame;
	private JButton addPlayer;
	private JButton replay;
	private JFrame frame;
	private Renderer renderer = new Renderer();
	private Image image;
	private Image player1,player2,player3,player4;
	private List<Image> players = new ArrayList<Image>(); 

	public GameUI(Game game) {
		game.addObserver(this);

		frame = new JFrame("Snake and ladder");
		ImageIcon imageIcon = new ImageIcon("src/8153987.jpg");
		image = imageIcon.getImage().getScaledInstance(750, 751, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon1 = new ImageIcon("src/player1.png");
		player1 = imageIcon1.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon("src/player2.png");
		player2 = imageIcon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon3 = new ImageIcon("src/player3.png");
		player3 = imageIcon3.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon4 = new ImageIcon("src/player4.png");
		player3 = imageIcon4.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		// JLabel label = new JLabel(new ImageIcon(image));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add(renderer, BorderLayout.CENTER);
		frame.add(new JPanel() {
			{
				restartGame = new JButton("restart");
				addPlayer = new JButton("Add Player");
				replay = new JButton("Replay");
				restartGame.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.restart();
						renderer.requestFocus();
					}
				});
				addPlayer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.addPlayer(new Player());
						renderer.requestFocus();
					}
				});
				
				replay.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						for(Player p : game.getPlayerList()) {
							p.getReplay();
							renderer.requestFocus();
						}
					}
				});
				add(restartGame);
				add(addPlayer);
				add(replay);
			}
		}, BorderLayout.SOUTH);
		frame.setSize(750, 810);
		renderer.requestFocus();
	}

	@Override
	public void update(Observable o, Object arg) {
		renderer.repaint();
	}

	class Renderer extends JPanel {
		private Game game;
		private int blockWidth = 75;

		public Renderer() {
			game = Game.getInstance();
			setDoubleBuffered(true);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			paintMap(g);
			paintBlocks(g);
		}

		private void paintMap(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}

		private void paintBlocks(Graphics g) {
			int picker = 0;
			for (Square b : game.getSquares()) {
				if (picker >= 4)
					picker = 0;
				g.drawImage(players.get(picker), b.getX()*blockWidth, b.getY()*blockWidth, null);
				picker++;
			}
		}

	}
}
