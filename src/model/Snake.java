package model;

import java.util.ArrayList;

import utils.Constants;

public class Snake {
	private ArrayList<Cell> bodySnake;
	// Trạng thái di chuyển của rắn (Nothing, Up, Down, Deft, Right);
	private String move;
	// Phương hướng của rắn (Up, Down, Left, Right);
	private String direction;

	
	//Khởi tạo con rắn
	public Snake() {
		this.bodySnake = new ArrayList<Cell>();
		for (int i = 0; i < Constants.INIT_SIZE; i++) {
			Cell cell = new Cell(Constants.WIDTH / 2 - i, Constants.HEIGHT / 2 - 1);
			this.bodySnake.add(cell);
		}
		this.move = "Nothing";
		this.direction = "Right";
	}

	public ArrayList<Cell> getBodySnake() {
		return bodySnake;
	}

	public void setBodySnake(ArrayList<Cell> bodySnake) {
		this.bodySnake = bodySnake;
	}

	public int getX() {
		return bodySnake.get(0).getX();
	}

	public int getY() {
		return bodySnake.get(0).getY();
	}
	
	public void setX(int i) {
		bodySnake.get(0).setX(i);
	}
	public void setY(int i) {
		bodySnake.get(0).setY(i);
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	
	//Hàm di chuyển của con rắn
	//Thêm 1 Cell mới vào đầu và xóa Cell cuối
	public void move() {
		if (move != "Nothing") {
			Cell first = bodySnake.get(0);
			Cell c = new Cell();

			if (move == "Up") {
				c.setX(first.getX());
				c.setY(first.getY() - 1);
			} else if (move == "Down") {
				c.setX(first.getX());
				c.setY(first.getY() + 1);
			} else if (move == "Left") {
				c.setX(first.getX() - 1);
				c.setY(first.getY());
			} else {
				c.setX(first.getX() + 1);
				c.setY(first.getY());
			}
			bodySnake.add(0, c);
			bodySnake.remove(bodySnake.size() - 1);
		}
	}

	//Hàm phát triển của rắn
	//Thêm 1 Cell mới vào đầu
	public void developpement() {
		Cell first = bodySnake.get(0);
		Cell c = new Cell();

		if (this.move == "Up") {
			c.setX(first.getX());
			c.setY(first.getY() - 1);
		} else if (this.move == "Down") {
			c.setX(first.getX());
			c.setY(first.getY() + 1);
		} else if (this.move == "Left") {
			c.setX(first.getX() - 1);
			c.setY(first.getY());
		} else {
			c.setX(first.getX() + 1);
			c.setY(first.getY());
		}
		this.bodySnake.add(0, c);
	}
	
	
	public void reset() {
		if (this.getX() < 0) {
			this.setX(Constants.WIDTH - 1);
		}
		if (this.getX() >= Constants.WIDTH) {
			this.setX(0);
		}
		if (this.getY() < 0) {
			this.setY(Constants.HEIGHT - 1);
		}
		if (this.getY() >= Constants.HEIGHT) {
			this.setY(0);
		}
	}

	//Hàm set phương hướng và trạng thái di chuyển
	public void up() {
		this.move = "Up";
		this.direction = "Up";
	}

	public void down() {
		this.move = "Down";
		this.direction = "Down";
	}

	public void left() {
		this.move = "Left";
		this.direction = "Left";
	}

	public void right() {
		this.move = "Right";
		this.direction = "Right";
	}
	
	public void destroy() {
		int r = this.bodySnake.size();
		this.bodySnake.remove(r-1);
	}

}
