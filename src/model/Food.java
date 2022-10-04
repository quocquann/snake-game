package model;

import utils.Constants;

public class Food extends Cell {
	
	
	public Food(Snake player) {
		this.random_food(player);
	}

	
	//Hàm sinh ngẫu nhiên vị trí đồ ăn(khác vị trí của rắn);
	public void random_food(Snake player) {
		boolean check_on_snake = true;
		while (check_on_snake) {
			check_on_snake = false;
			super.setX((int) (Math.random() * Constants.WIDTH - 1));
			super.setY((int) (Math.random() * Constants.HEIGHT - 1));
			for (Cell c : player.getBodySnake()) {
				if (c.getX() == super.getX() && c.getY() == super.getY()) {
					check_on_snake = true;
				}
			}
		}
	}
}
