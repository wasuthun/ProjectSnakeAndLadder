import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GameUI extends JFrame implements Observer {

	private Renderer renderer;
	private JButton restartGame;
	private JButton addPlayer;
	private JFrame frame;
	private Image image;

	public GameUI(Game game) {
		game.addObserver(this);
		renderer = new Renderer();
		frame = new JFrame("Snake and ladder");
		setLayout(new BorderLayout());
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
		pack();
		frame.setSize(700, 770);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		renderer.requestFocus();
	}

	@Override
	public void update(Observable o, Object arg) {
		renderer.repaint();
	}

}

class Renderer extends JPanel {

	private int blockWidth = 10;
	private int blockSize = 7;
	private int mapSize;
	private Game game;

	public Renderer() {
		game = Game.getInstance();
		mapSize = game.getBoardSize() * blockWidth;
		setPreferredSize(new Dimension(mapSize, mapSize));
		setDoubleBuffered(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintGrids(g);
		paintBlocks(g);
	}

	private void paintGrids(Graphics g) {
		Color blue = new Color(72, 129, 234);
		Color red = new Color(233, 22, 64);
		Color yellow = new Color(255, 219, 77);
		List<Color> c = new ArrayList<Color>();
		c.add(blue);
		c.add(red);
		c.add(yellow);
		int picker = 0;
		for (int j = 0; j < blockWidth; j++) {
			for (int i = 0; i < blockWidth; i++) {
				if (picker >= 3)
					picker = 0;
				g.setColor(c.get(picker));
				g.fillRect(i * blockWidth * blockSize, j * blockWidth * blockSize, blockSize * blockWidth,
						blockSize * blockWidth);
				picker++;
				g.setColor(Color.gray);
				g.drawRect(i * blockWidth * blockSize, j * blockWidth * blockSize, blockSize * blockWidth,
						blockSize * blockWidth);
			}
		}
	}

	private void paintBlocks(Graphics g) {
		Color green = new Color(154, 205, 50);
		Color gray = new Color(217, 217, 217);
		Color purple = new Color (191, 128, 255);
		Color black =new Color(0, 0, 0);
		List<Color> c = new ArrayList<Color>();
		c.add(green);
		c.add(gray);
		c.add(purple);
		c.add(black);
		int picker = 0;
		for (Square b : game.getSquares()) {
			if (picker >= 4)
				picker = 0;
			g.setColor(c.get(picker));
			g.fillOval(b.getX() * blockWidth * blockSize, b.getY() * blockWidth * blockSize, blockWidth * blockSize,
					blockWidth * blockSize);

			picker++;
		}
	}
}
