import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GameUI extends JFrame{

	private JButton restartGame;
	private JButton addPlayer;
	private JFrame frame;
	private Image image;

	public GameUI(Game game) {
		frame = new JFrame("Snake and ladder");
		ImageIcon imageIcon = new ImageIcon("/Users/mark/Documents/OOP/ProjectSnakeAndLadder/src/8153987.jpg");
		image = imageIcon.getImage().getScaledInstance(500, 501, java.awt.Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(image));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
		setLayout(new BorderLayout());
		frame.add(new JPanel() {
			{
				restartGame = new JButton("restart");
				addPlayer = new JButton("Add Player");
				restartGame.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.restart();
					}
				});
				addPlayer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.addPlayer(new Player());
					}
				});
				add(restartGame);
				add(addPlayer);
			}
		}, BorderLayout.SOUTH);
		frame.setSize(500, 560);
	}
}
