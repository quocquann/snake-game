package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Game;
import utils.Constants;
import utils.IOFileData;

public class SnakeGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;

	
	//Yêu cầu ở từng menu
	
	//start menu
	private int commandNumSM; 
	//pause menu
	private int commandNumPM;
	//end menu
	private int commandNumEM;
	//level menu
	private int commandNumLM;

	Image snake;
	Image food;
	Image snakeEnd;

	public SnakeGamePanel() {
		this.game = new Game();
		this.commandNumSM = 0;
		this.commandNumPM = 0;
		this.commandNumEM = 0;
		this.commandNumLM = 0;
		this.setPreferredSize(
				new Dimension(Constants.WIDTH * Constants.DIMENSION, Constants.HEIGHT * Constants.DIMENSION));
		this.setDoubleBuffered(false);
	}

	public void newGame() {
		this.game = new Game();
		this.commandNumSM = 0;
		this.commandNumPM = 0;
		this.commandNumEM = 0;
		this.commandNumLM = 0;
	}

	public void loadGame() {
		this.game = new Game(IOFileData.readFile(Constants.FILE_NAME_DATA));
		this.commandNumSM = 0;
		this.commandNumPM = 0;
		this.commandNumEM = 0;
		this.commandNumLM = 0;

	}

	public int getCommandNumSM() {
		return commandNumSM;
	}

	public void setCommandNumSM(int commandNumSM) {
		this.commandNumSM = commandNumSM;
	}

	public int getCommandNumPM() {
		return commandNumPM;
	}

	public void setCommandNumPM(int commandNumPM) {
		this.commandNumPM = commandNumPM;
	}

	public int getCommandNumEM() {
		return commandNumEM;
	}

	public void setCommandNumEM(int commandNumEM) {
		this.commandNumEM = commandNumEM;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getCommandNumLM() {
		return commandNumLM;
	}

	public void setCommandNumLM(int commandNumLM) {
		this.commandNumLM = commandNumLM;
	}

	
	//Hàm vẽ thức ăn
	public void drawFood(Graphics g) {
		food = new ImageIcon(Constants.FOOD_IMAGE).getImage();
		g.drawImage(food, this.game.getFood().getX() * Constants.DIMENSION,
				this.game.getFood().getY() * Constants.DIMENSION, Constants.DIMENSION, Constants.DIMENSION, null);
		g.setColor(Color.BLUE);
	}

	
	//Hàm vẽ con rắn
	public void drawSnake(Graphics g) {
		int s = this.game.getSnake().getBodySnake().size();
		for (int i = 0; i < s; i++) {
			if (i == 0) {
				g.setColor(Color.white);
				g.fillRect(this.game.getSnake().getBodySnake().get(i).getX() * Constants.DIMENSION,
						this.game.getSnake().getBodySnake().get(i).getY() * Constants.DIMENSION, Constants.DIMENSION,
						Constants.DIMENSION);
			} else {
				g.setColor(Color.blue);
				g.fillRect(this.game.getSnake().getBodySnake().get(i).getX() * Constants.DIMENSION,
						this.game.getSnake().getBodySnake().get(i).getY() * Constants.DIMENSION, Constants.DIMENSION,
						Constants.DIMENSION);
			}
		}
	}

	
	//Hàm vẽ điểm
	public void drawScore(Graphics g) {
		String text = "Score: " + this.game.getScore();
		g.setColor(Color.white);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		int x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		int y = Constants.HEIGHT * Constants.DIMENSION - 20;
		g.drawString(text, x, y);
	}

	//Hàm vẽ menu start
	public void drawMenuStart(Graphics g) {

		String text = "SNAKE GAME";
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
		g.setColor(Color.white);
		int x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		int y = (Constants.WIDTH * Constants.DIMENSION) / 4;
		g.drawString(text, x, y);

		snake = new ImageIcon(Constants.SNAKE_IMAGE).getImage();
		int imgWidth = 200;
		int imgHeight = 200;
		g.drawImage(snake, (Constants.WIDTH * Constants.DIMENSION - imgWidth) / 2,
				(Constants.HEIGHT * Constants.DIMENSION - imgHeight) / 2, imgWidth, imgHeight, null);

		int space = 20;
		text = "NEW GAME";
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y = (Constants.WIDTH * Constants.DIMENSION) / 4 * 3;
		g.drawString(text, x, y);
		if (commandNumSM == 0) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		text = "LOAD GAME";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumSM == 1) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		text = "QUIT";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumSM == 2) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

	}

	
	//Hàm vẽ menu pause
	public void drawMenuPause(Graphics g) {
		int space = 20;
		String text = "RESUME";
		g.setColor(Color.white);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		int x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		int y = (Constants.WIDTH * Constants.DIMENSION) / 2;
		g.drawString(text, x, y);
		if (commandNumPM == 0) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		text = "MAIN MENU";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumPM == 1) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}
	}

	
	//Hàm vẽ menu end
	public void drawMenuEnd(Graphics g) {
		int space = 20;
		

		snakeEnd = new ImageIcon(Constants.SNAKE_IMAGE_END).getImage();
		int imgWidth = 200;
		int imgHeight = 200;
		g.drawImage(snakeEnd, (Constants.WIDTH * Constants.DIMENSION - imgWidth) / 2,
				(Constants.HEIGHT * Constants.DIMENSION - imgHeight) / 2, imgWidth, imgHeight, null);


		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
		String text = "YOU LOSE";
		g.setColor(Color.white);
		int x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		int y = (Constants.WIDTH * Constants.DIMENSION) / 4;
		g.drawString(text, x, y);

		
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		text = "YOUR SCORE: " + this.game.getScore();
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y = (Constants.WIDTH * Constants.DIMENSION) / 4 * 3;
		g.drawString(text, x, y);

		
		text = "HIGHT SCORE: " + this.game.getHightScore();
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);

		
		text = "NEW GAME";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumEM == 0) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		
		text = "QUIT";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumEM == 1) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}
	}

	
	//Hàm vẽ level menu
	public void drawLevelMenu(Graphics g) {

		String text = "SNAKE GAME";
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
		g.setColor(Color.white);
		int x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		int y = (Constants.WIDTH * Constants.DIMENSION) / 4;
		g.drawString(text, x, y);

		
		snake = new ImageIcon(Constants.SNAKE_IMAGE).getImage();
		int imgWidth = 200;
		int imgHeight = 200;
		g.drawImage(snake, (Constants.WIDTH * Constants.DIMENSION - imgWidth) / 2,
				(Constants.HEIGHT * Constants.DIMENSION - imgHeight) / 2, imgWidth, imgHeight, null);


		int space = 20;
		text = "EASY";
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y = (Constants.WIDTH * Constants.DIMENSION) / 4 * 3;
		g.drawString(text, x, y);
		if (commandNumLM == 0) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		
		text = "NORMAL";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumLM == 1) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		
		text = "HARD";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumLM == 2) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}

		
		text = "BACK";
		g.setColor(Color.white);
		x = (Constants.WIDTH * Constants.DIMENSION - g.getFontMetrics().stringWidth(text)) / 2;
		y += space;
		g.drawString(text, x, y);
		if (commandNumLM == 3) {
			g.setColor(Color.white);
			g.drawString(">", x - space, y);
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(79, 173, 113));
		g.fillRect(0, 0, Constants.WIDTH * Constants.DIMENSION, Constants.HEIGHT * Constants.DIMENSION);
		if (this.game.getState() == "START") {
			//State game = "START" thì vẽ start menu
			this.drawMenuStart(g);
		} else if (this.game.getState() == "CHOOSE_LEVEL") {
			//State game = "CHOOSE_LEVEL" thì vẽ level menu
			this.drawLevelMenu(g);
		} else if (this.game.getState() == "RUNNING") {
			//State game = "RUNNING" thì vẽ rắn, đồ ăn, điểm
			this.drawFood(g);
			this.drawSnake(g);
			this.drawScore(g);
		} else if (this.game.getState() == "PAUSE") {
			//State game = "PAUSE" thì vẽ pause menu
			this.drawMenuPause(g);
		} else {
			//State game = "END" thì vẽ end menu
			this.drawMenuEnd(g);
		}
	}

}
