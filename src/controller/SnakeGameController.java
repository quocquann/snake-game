package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import utils.Constants;
import utils.IOFileData;
import view.SnakeGameView;

public class SnakeGameController implements KeyListener, ActionListener {

	private SnakeGameView snakeGameView;
	Timer timer;

	public SnakeGameController(SnakeGameView snakeGameView) {
		this.snakeGameView = snakeGameView;
		timer = new Timer(Constants.DELAY_NORMAL, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (this.snakeGameView.getSnakePanel().getGame().getState() == "START") {
			
			//Nhận sự kiện và vẽ lại start menu
			//Sử dụng các phím mũi tên để di chuyển qua các option
			if (keyCode == KeyEvent.VK_UP) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumSM(this.snakeGameView.getSnakePanel().getCommandNumSM() - 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumSM() < 0) {
					this.snakeGameView.getSnakePanel().setCommandNumSM(2);
				}

			}
			if (keyCode == KeyEvent.VK_DOWN) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumSM(this.snakeGameView.getSnakePanel().getCommandNumSM() + 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumSM() > 2) {
					this.snakeGameView.getSnakePanel().setCommandNumSM(0);
				}

			}
			if (keyCode == KeyEvent.VK_ENTER) {
				if (this.snakeGameView.getSnakePanel().getCommandNumSM() == 0) {
					this.snakeGameView.getSnakePanel().getGame().chooseLevel();
					this.snakeGameView.getSnakePanel().repaint();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumSM() == 1) {
					this.snakeGameView.getSnakePanel().loadGame();
					this.snakeGameView.getSnakePanel().getGame().start();
					this.timer.setDelay(IOFileData.readFromFile(Constants.DELAY_FILE_NAME));
					this.timer.start();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumSM() == 2) {
					this.timer.stop();
					System.exit(0);
				}
			}
		}

		else if (this.snakeGameView.getSnakePanel().getGame().getState() == "CHOOSE_LEVEL") {
			
			//Nhận sự kiện và vẽ lại level menu
			//Sử dụng các phím mũi tên để di chuyển qua các option
			if (keyCode == KeyEvent.VK_UP) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumLM(this.snakeGameView.getSnakePanel().getCommandNumLM() - 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumLM() < 0) {
					this.snakeGameView.getSnakePanel().setCommandNumLM(3);
				}

			} else if (keyCode == KeyEvent.VK_DOWN) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumLM(this.snakeGameView.getSnakePanel().getCommandNumLM() + 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumLM() > 3) {
					this.snakeGameView.getSnakePanel().setCommandNumLM(0);
				}

			} else if (keyCode == KeyEvent.VK_ENTER) {
				if (this.snakeGameView.getSnakePanel().getCommandNumLM() == 0) {
					
					//Chọn "EASY"
					//Bắt đầu game
					this.snakeGameView.getSnakePanel().newGame();
					this.snakeGameView.getSnakePanel().getGame().start();
					IOFileData.writeToFile(Constants.DELAY_FILE_NAME, Constants.DELAY_EASY);
					timer.setDelay(Constants.DELAY_EASY);
					this.timer.start();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumLM() == 1) {
					//Chọn "NORMAL"
					//Bắt đầu game
					this.snakeGameView.getSnakePanel().newGame();
					this.snakeGameView.getSnakePanel().getGame().start();
					IOFileData.writeToFile(Constants.DELAY_FILE_NAME, Constants.DELAY_NORMAL);
					timer.setDelay(Constants.DELAY_NORMAL);
					this.timer.start();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumLM() == 2) {
					//Chọn "HARD"
					//Bắt đầu game
					this.snakeGameView.getSnakePanel().newGame();
					this.snakeGameView.getSnakePanel().getGame().start();
					IOFileData.writeToFile(Constants.DELAY_FILE_NAME, Constants.DELAY_HARD);
					timer.setDelay(Constants.DELAY_HARD);
					this.timer.start();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumLM() == 3) {
					//Chọn "BACK"
					//Chuyển về start menu
					this.snakeGameView.getSnakePanel().getGame().setState("START");
					this.snakeGameView.getSnakePanel().repaint();
				}
			}
		}

		else if (this.snakeGameView.getSnakePanel().getGame().getState() == "RUNNING") {
			
			//Nhận sự kiện và vẽ lại chuyển động của rắn khi đang chơi
			//Sử dụng các phím mũi tên để điều khiển con rắn
			
			if (keyCode == KeyEvent.VK_UP
					&& !this.snakeGameView.getSnakePanel().getGame().getSnake().getDirection().equals("Down")) { // Move
																													// up
				this.snakeGameView.getSnakePanel().getGame().getSnake().up();

			} else if (keyCode == KeyEvent.VK_DOWN
					&& !this.snakeGameView.getSnakePanel().getGame().getSnake().getDirection().equals("Up")) { // Move
																												// down
				this.snakeGameView.getSnakePanel().getGame().getSnake().down();

			}

			else if (keyCode == KeyEvent.VK_LEFT
					&& !this.snakeGameView.getSnakePanel().getGame().getSnake().getDirection().equals("Right")) { // Move
																													// left
				this.snakeGameView.getSnakePanel().getGame().getSnake().left();

			}

			else if (keyCode == KeyEvent.VK_RIGHT
					&& !this.snakeGameView.getSnakePanel().getGame().getSnake().getDirection().equals("Left")) { // Move
																													// down
				this.snakeGameView.getSnakePanel().getGame().getSnake().right();

			} else if (keyCode == KeyEvent.VK_SPACE) {
				
				//Nhấn Space để pause game
				this.snakeGameView.getSnakePanel().getGame().setState("PAUSE");
				
			}
		} else if (this.snakeGameView.getSnakePanel().getGame().getState() == "PAUSE") {
			//Nhận sự kiện và vẽ lại pause menu
			//Sử dụng các phím mũi tên để di chuyển qua các option
			if (keyCode == KeyEvent.VK_UP) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumPM(this.snakeGameView.getSnakePanel().getCommandNumPM() - 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumPM() < 0) {
					this.snakeGameView.getSnakePanel().setCommandNumPM(1);
				}

			} else if (keyCode == KeyEvent.VK_DOWN) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumPM(this.snakeGameView.getSnakePanel().getCommandNumPM() + 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumPM() > 1) {
					this.snakeGameView.getSnakePanel().setCommandNumPM(0);
				}

			} else if (keyCode == KeyEvent.VK_ENTER) {
				if (this.snakeGameView.getSnakePanel().getCommandNumPM() == 0) {
					//Chọn "RESUME"
					//Tiếp tục chơi
					this.snakeGameView.getSnakePanel().loadGame();
					this.snakeGameView.getSnakePanel().getGame().start();
					this.timer.start();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumPM() == 1) {
					//Chọn "MAIN MENU"
					//Quay lại start menu
					this.snakeGameView.getSnakePanel().getGame().setState("START");
				}
			}
		} else if (this.snakeGameView.getSnakePanel().getGame().getState() == "END") {		
			//Nhận sự kiện và vẽ lại end menu
			//Sử dụng các phím mũi tên để di chuyển qua các option
			
			if (keyCode == KeyEvent.VK_UP) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumEM(this.snakeGameView.getSnakePanel().getCommandNumEM() - 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumEM() < 0) {
					this.snakeGameView.getSnakePanel().setCommandNumEM(1);
				}

			} else if (keyCode == KeyEvent.VK_DOWN) {
				this.snakeGameView.getSnakePanel()
						.setCommandNumEM(this.snakeGameView.getSnakePanel().getCommandNumEM() + 1);
				this.snakeGameView.getSnakePanel().repaint();
				if (this.snakeGameView.getSnakePanel().getCommandNumEM() > 1) {
					this.snakeGameView.getSnakePanel().setCommandNumEM(0);
				}

			} else if (keyCode == KeyEvent.VK_ENTER) {
				if (this.snakeGameView.getSnakePanel().getCommandNumEM() == 0) {
					//chọn "NEW GAME" trong end menu
					//chuyển đến level menu
					
					this.snakeGameView.getSnakePanel().getGame().chooseLevel();
				}
				if (this.snakeGameView.getSnakePanel().getCommandNumEM() == 1) {
					
					//Chọn "QUIT"
					//Thoát trò chơi
					this.timer.stop();
					System.exit(0);
				}
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.snakeGameView.getSnakePanel().getGame().update();
		this.snakeGameView.getSnakePanel().repaint();
	}
}
