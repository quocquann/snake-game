package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Cell;
import model.Game;

public class IOFileData {

	public static void writeFile(String fileName, Game game) {
		File file = new File(fileName);

		FileWriter fw = null;
		BufferedWriter bw = null;

		int s = game.getSnake().getBodySnake().size();
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(s + "");
			for (int i = 0; i < s; i++) {
				bw.newLine();
				bw.write(game.getSnake().getBodySnake().get(i).getX() + " "
						+ game.getSnake().getBodySnake().get(i).getY());
			}

			bw.newLine();
			bw.write(game.getFood().getX() + " " + game.getFood().getY());

			bw.newLine();
			bw.write(game.getScore() + "");

			bw.newLine();
			bw.write(game.getSnake().getDirection());

		} catch (IOException e) {
			//N.A
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				//N.A
			}
		}
	}

	public static Game readFile(String fileName) {
		Game game = new Game();
		File file = new File(fileName);

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line = null;
			line = br.readLine();
			int s = Integer.parseInt(line);
			int i = 0;
			ArrayList<Cell> snake = new ArrayList<>();
			for (i = 0; i < s; i++) {
				line = br.readLine();
				String[] ls = line.split(" ");
				int x = Integer.parseInt(ls[0]);
				int y = Integer.parseInt(ls[1]);
				Cell cell = new Cell(x, y);
				snake.add(cell);
			}
			game.getSnake().setBodySnake(snake);

			line = br.readLine();
			String[] ls = line.split(" ");
			int x = Integer.parseInt(ls[0]);
			int y = Integer.parseInt(ls[1]);
			game.getFood().setX(x);
			game.getFood().setY(y);

			line = br.readLine();
			game.setScore(Integer.parseInt(line));
			line = br.readLine();

			game.getSnake().setDirection(line);
		} catch (FileNotFoundException e) {
			Game g = new Game();
			return g;
		} catch (IOException e) {
			//N.A
		} finally {
			try {
				br.close();
				fr.close();
			} catch (NullPointerException | IOException e) {
				//N.A
			}
		}
		return game;
	}

	public static void clearFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

	
	public static void writeToFile(String fileName, int num) {
		File file = new File(fileName);

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(num + "");
		} catch (IOException e) {
			//N.A
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				//N.A
			}
		}
	}

	public static int readFromFile(String fileName) {

		File file = new File(fileName);

		FileReader fr = null;
		BufferedReader br = null;

		int result = 0;
		String line = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			line = br.readLine();

			result = Integer.parseInt(line);

		} catch (FileNotFoundException e) {
			//N.A
		} catch (IOException e) {
			//N.A
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				//N.A
			}
		}
		return result;
	}
}
