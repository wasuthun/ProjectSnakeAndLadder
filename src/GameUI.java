import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUI implements Observer {

	private JButton restartGame;
	private JButton addPlayer;
	private JFrame frame;
	private Renderer renderer = new Renderer();
	private Image image;
	private Image player1;

	public GameUI(Game game) {
		game.addObserver(this);
		
		frame = new JFrame("Snake and ladder");
		ImageIcon imageIcon = new ImageIcon("src/8153987.jpg");
		image = imageIcon.getImage().getScaledInstance(500, 501, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon("src/player1.png");
		player1 = imageIcon2.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		//JLabel label = new JLabel(new ImageIcon(image));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add(renderer, BorderLayout.CENTER);
		frame.add(new JPanel() {
			{
				restartGame = new JButton("restart");
				addPlayer = new JButton("Add Player");
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
				add(restartGame);
				add(addPlayer);
			}
		}, BorderLayout.SOUTH);
		frame.setSize(500, 560);
		renderer.requestFocus();
		}
	
	@Override
	public void update(Observable o, Object arg) {
		renderer.repaint();
	}

class Renderer extends JPanel {
	private Game game;
	private int blockWidth = 10;
	private int mapSize;
	
	public Renderer() {
		game = Game.getInstance();
		mapSize = game.getBoardSize() * blockWidth;
		setPreferredSize(new Dimension(mapSize, mapSize));
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
		//g.setColor(Color.black);
		for (Square b : game.getSquares()) {
			g.drawImage(player1, b.getX(), b.getY(), null);
		}
	}
	
}
}
