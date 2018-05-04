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
import javax.swing.Timer;

public class GameUI implements Observer {
	private JFrame frame;
	private String[] item = { "Add player", "Restart", "Replay" };
	private Renderer renderer = new Renderer();
	private Image image;
	private Image player1, player2, player3, player4;
	private List<Image> players = new ArrayList<Image>();
	private Game game;

	public GameUI(Game game) {
		game.addObserver(this);
		this.game = game;

		frame = new JFrame("Snake and ladder");
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		bar.add(menu);

		ImageIcon imageIcon = new ImageIcon("src/8153987.jpg");
		image = imageIcon.getImage().getScaledInstance(750, 751, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon1 = new ImageIcon("src/player1.png");
		player1 = imageIcon1.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon("src/player2.png");
		player2 = imageIcon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon3 = new ImageIcon("src/player3.png");
		player3 = imageIcon3.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon4 = new ImageIcon("src/player4.png");
		player4 = imageIcon4.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
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
							// TODO Auto-generated catch block
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
			}
		}
		bar.add(menu);
		frame.add(bar, BorderLayout.NORTH);
		frame.setSize(750, 810);
		renderer.requestFocus();
	}

	private void moveAnimation(Player player) {
		// piece go back when go more than Board size

		Timer timer = new Timer(10, null);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Square oldPos = new Square(player.getOldPosition().getX(), player.getOldPosition().getY());
				Square newPos = new Square(player.getPosition().getX(), player.getPosition().getY());
				// แถวเดียว
				if (oldPos.getY() == newPos.getY()) {
					if (oldPos.getX() < newPos.getX())
						for (int i = oldPos.getX(); i <= newPos.getX(); i++) {
							oldPos.setX(i);
							renderer.repaint();
						}
					else if (oldPos.getX() > newPos.getX())
						for (int i = oldPos.getX(); i >= newPos.getX(); i--) {
							oldPos.setX(i);
							renderer.repaint();
						}
				}
				// เดินขึ้น1แถว
				else if (oldPos.getY() - 1 == newPos.getY()) {
					if (oldPos.getY() % 2 == 1) {
						for (int i = oldPos.getX(); i <= 9; i++) {
							oldPos.setX(i);
							renderer.repaint();
						}
						oldPos.setY(oldPos.getY() - 1);
						for (int j = 9; j >= newPos.getX(); j--) {
							oldPos.setX(j);
							renderer.repaint();
						}
					} else {
						for (int i = oldPos.getX(); i >= 0; i--) {
							oldPos.setX(i);
							renderer.repaint();
						}
						oldPos.setY(oldPos.getY() - 1);
						for (int j = 0; j <= newPos.getX(); j++) {
							oldPos.setX(j);
							renderer.repaint();
						}
					}
				}
				// ขึ้น2แถว
				else if (oldPos.getY() - 2 == newPos.getY()) {
					if (oldPos.getY() % 2 == 1) {
						for (int i = oldPos.getX(); i <= 9; i++) {
							oldPos.setX(i);
							renderer.repaint();
						}
						oldPos.setY(oldPos.getY() - 1);
						for (int j = 9; j >= 0; j--) {
							oldPos.setX(j);
							renderer.repaint();
						}
						oldPos.setY(oldPos.getY() - 1);
						for (int k = 0; k <= newPos.getX(); k++) {
							oldPos.setX(k);
							renderer.repaint();
						}

					} else {
						for (int i = oldPos.getX(); i >= 0; i--) {
							oldPos.setX(i);
							renderer.repaint();
						}
						oldPos.setY(oldPos.getY() - 1);
						for (int j = 0; j <= 9; j++) {
							oldPos.setX(j);
							renderer.repaint();
						}
						oldPos.setY(oldPos.getY() - 1);
						for (int k = 9; k >= newPos.getX(); k--) {
							oldPos.setX(k);
							renderer.repaint();
						}
					}
				}
			}
		});
		timer.start();
	}

	@Override
	public void update(Observable o, Object arg) {

//		if (game.getPlayer() == null) {
			renderer.repaint();
//		} else {
//			moveAnimation(game.getPlayer());
//		}

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
			List<Square> squares = game.getSquares();
			Square[] squaresArr = new Square[squares.size()];
			squaresArr = squares.toArray(squaresArr);
			//System.out.println(squares);
			for (Square b : squaresArr) {
				if (picker >= 4)
					picker = 0;
				g.drawImage(players.get(picker), b.getX() * blockWidth, b.getY() * blockWidth, this);
				picker++;
			}
		}

	}
}
