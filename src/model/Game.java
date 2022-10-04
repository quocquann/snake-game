package model;

import utils.Constants;
import utils.IOFileData;

public class Game {
	private Snake player;
	private Food food;
	private int score;
	private String state;
	private static int hightScore = 0;


	public Game() {
		this.player = new Snake();
		this.food = new Food(player);
		this.state = "START";
		this.score = 0;
	}

	public Game(Game game) {
		this.player = game.getSnake();
		this.food = game.getFood();
		this.score = game.getScore();
		this.state = "RUNNING";
	}

	public Snake getSnake() {
		return player;
	}

	public void setSnake(Snake snake) {
		this.player = snake;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHightScore() {
		return hightScore;
	}


	// Đổi trạng thái của game thành "RUNNING"
	public void start() {
		this.state = "RUNNING";
	}

	// Đổi trạng thái của game thành "CHOOSE_LEVEL"
	public void chooseLevel() {
		this.state = "CHOOSE_LEVEL";
	}

	// Hàm update game và ghi file sau mỗi bước di chuyển
	public void update() {
		if (this.state == "RUNNING") {
			if (check_food()) {
				IOFileData.writeFile(Constants.FILE_NAME_DATA, this);
				this.player.developpement();
				this.increaseScore();
				this.food.random_food(player);
			} else if (check_body()) {
				updateHighScore(this.score);
				IOFileData.clearFile(Constants.FILE_NAME_DATA);
				this.state = "END";
			} else if (check_wall()) {
				player.reset();
				IOFileData.writeFile(Constants.FILE_NAME_DATA, this);
			} else {
				IOFileData.writeFile(Constants.FILE_NAME_DATA, this);
				player.move();
			}
		}
	}

	// Kiểm tra rắn đâm vào tường
	public boolean check_wall() {
		if (player.getX() < 0 || player.getX() >= Constants.WIDTH || player.getY() < 0
				|| player.getY() >= Constants.HEIGHT) {
			return true;
		}
		return false;
	}

	// Kiểm tra rắn ăn đồ ăn
	public boolean check_food() {
		if (player.getX() == food.getX() && player.getY() == food.getY()) {
			return true;
		}
		return false;
	}

	// Kiểm tra rắn cắn vào thân
	public boolean check_body() {
		for (int i = 1; i < player.getBodySnake().size(); i++) {
			if (player.getX() == player.getBodySnake().get(i).getX()
					&& player.getY() == player.getBodySnake().get(i).getY()) {
				return true;
			}
		}

		return false;
	}

	public boolean checkDead() {
		if (this.getSnake().getBodySnake().size() <= 0) {
			return true;
		}
		return false;
	}

	// Hàm tăng điểm
	public void increaseScore() {
		this.score++;
	}

	public void decreses() {
		this.score--;
	}

	// Hàm cập nhật điểm cao
	public static void updateHighScore(int score) {
		Game.hightScore = IOFileData.readFromFile(Constants.FILE_NAME_HIGH_SCORE);
		if (score > Game.hightScore) {
			Game.hightScore = score;
			IOFileData.writeToFile(Constants.FILE_NAME_HIGH_SCORE, Game.hightScore);
		}
	}
}
