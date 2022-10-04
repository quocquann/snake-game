package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import controller.SnakeGameController;
import utils.Constants;

public class SnakeGameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SnakeGamePanel snakePanel;
	Image snakeIcon;

	public SnakeGamePanel getSnakePanel() {
		return snakePanel;
	}

	public void setSnakePanel(SnakeGamePanel snakePanel) {
		this.snakePanel = snakePanel;
	}

	public SnakeGameView() {
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		snakeIcon = new ImageIcon(Constants.SNAKE_IMAGE).getImage();
		this.setIconImage(snakeIcon);		
		SnakeGameController snc = new SnakeGameController(this);
		this.snakePanel = new SnakeGamePanel();
		this.addKeyListener(snc);
		this.setContentPane(snakePanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}
