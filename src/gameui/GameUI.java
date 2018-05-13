package gameui;

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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import game.Game;
import game.Square;
import player.Player;

/**
 * This class is a UI class of game that have a many function of this game that
 * we build for user in UI
 * 
 * @author Wasuthun and Patcharapol
 *
 */
public class GameUI implements Observer {
	private JFrame frame;
	private String[] item = { "Add player", "Save", "Load", "Restart", "Replay" };
	private Renderer renderer = new Renderer();
	private Image image;
	private Image player1, player2, player3, player4;
	private List<Image> players = new ArrayList<Image>();
	private Game.Memento m;

	/**
	 * Constructor
	 * 
	 * @param game
	 */
	public GameUI(Game game) {
		game.addObserver(this);

		frame = new JFrame("Snake and ladder");
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		bar.add(menu);

		ImageIcon imageIcon = new ImageIcon("src/8153987.jpg");
		image = imageIcon.getImage().getScaledInstance(750, 751, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon1 = new ImageIcon("src/player1.png");
		player1 = imageIcon1.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon("src/player2.png");
		player2 = imageIcon2.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon3 = new ImageIcon("src/player3.png");
		player3 = imageIcon3.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon4 = new ImageIcon("src/player4.png");
		player4 = imageIcon4.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add(renderer, BorderLayout.CENTER);

		for (String x : item) {
			JMenuItem mitem = new JMenuItem(x);
			menu.add(mitem);
			if (x.equals("Restart")) {
				mitem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.restart();
						game.getPointListReplay().clear();
						renderer.requestFocus();
					}
				});
			} else if (x.equals("Replay")) {
				mitem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							game.getReplay();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						renderer.requestFocus();
					}
				});
			} else if (x.equals("Add player")) {
				mitem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.addPlayer(new Player());
						renderer.requestFocus();
					}
				});
			} else if (x.equals("Save")) {
				mitem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						m = game.save();
						renderer.requestFocus();
					}
				});
			} else if (x.equals("Load")) {
				mitem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.load(m);
						renderer.requestFocus();
					}
				});
			}
		}
		bar.add(menu);
		frame.add(bar, BorderLayout.NORTH);
		frame.setSize(750, 810);
		renderer.requestFocus();
	}

	/**
	 * Use to update new position of player to GameUI
	 */
	@Override
	public void update(Observable o, Object arg) {
		renderer.repaint();
	}

	/**
	 * This class use to draw a picture of player and board to UI
	 * 
	 * @author Wasuthun and Patcharapol
	 *
	 */
	class Renderer extends JPanel {
		private Game game;
		private int blockWidth = 75;

		/**
		 * Constructor
		 */
		public Renderer() {
			game = Game.getInstance();
			setDoubleBuffered(true);
		}

		/**
		 * Paint map and players
		 */
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			paintMap(g);
			paintBlocks(g);
		}

		/**
		 * Use to draw a map
		 * 
		 * @param g
		 */
		private void paintMap(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}

		/**
		 * Use to draw a players
		 * 
		 * @param g
		 */
		private void paintBlocks(Graphics g) {
			int picker = 0;
			List<Square> squares = game.getSquares();
			Square[] squaresArr = new Square[squares.size()];
			squaresArr = squares.toArray(squaresArr);
			for (Square b : squaresArr) {
				if (picker >= 4)
					picker = 0;
				g.drawImage(players.get(picker), b.getX() * blockWidth, b.getY() * blockWidth, this);
				picker++;
			}
		}

	}
}
