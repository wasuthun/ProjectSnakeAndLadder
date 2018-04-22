import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;





public class GameUI extends JFrame implements Observer{

	private Renderer renderer;
	private JButton resetGame;
	public GameUI(Game game) {
		game.addObserver(this);
		renderer=new Renderer();
	
		setLayout(new BorderLayout());
		add(renderer, BorderLayout.CENTER);
        add(new JPanel() {
            {
            		resetGame = new JButton("reset");
            		resetGame.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							game.reset();
							renderer.requestFocus();
						}
					});
            }
        }, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        renderer.requestFocus();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

}
class Renderer extends JPanel {

    private int blockWidth = 10;
    private int mapSize;
	private Game game;

    public Renderer() {
    		game=Game.getInstance();
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
        g.setColor(Color.gray);
        for (int i = 0; i < mapSize; i++) {
            g.drawLine(i * blockWidth, 0, i * blockWidth, getHeight());
            g.drawLine(0, i * blockWidth, getWidth(), i * blockWidth);
        }
    }

    private void paintBlocks(Graphics g) {
        g.setColor(Color.red);
        for(Square b : game.getSquares()) {
            g.fillRect(b.getX() * blockWidth, b.getY() * blockWidth, blockWidth, blockWidth);
        }
    }
}


