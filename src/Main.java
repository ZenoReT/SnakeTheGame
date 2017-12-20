import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

import fieldObjects.SnakeHead;
import game.Field;
import game.Game;
import gui.CUI;
import gui.GUI;
import gui.SnakeCustomizer;
import levels.Level;
import levels.Level1;
import utils.Consts;
import utils.Point;

public class Main {

	public static void main(String[] args) throws IOException {

		Level level = new Level1(Consts.levels[new Random().nextInt(Consts.levels.length)]);
		Game game = new Game(level);
		SnakeHead snakeHead = game.findSnakeHead();
		if (args.length == 0) {
			startGui(level.getField(), game, snakeHead);
		}
		else {
			startCui(level.getField(), game, snakeHead);
		}
		System.exit(0);
	}
	
	private static void startGui(Field field, Game game, SnakeHead snakeHead) {
		int size = 50;	
		GUI gui = new GUI(game, size, snakeHead);
	}
	
	private static void startCui(Field field, Game game, SnakeHead snakeHead) {
		Scanner in = new Scanner(System.in);
		while (!game.gameOver) {
			try {
				TimeUnit.MILLISECONDS.sleep(game.getSpeed());
			} 
			catch (InterruptedException e) {}
			CUI.print(field);
			String command = in.next();	
			game.changeDirection(command, snakeHead);
			game.tick();
		}
		in.close();
	}
}
