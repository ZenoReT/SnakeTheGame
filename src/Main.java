import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import fieldObjects.SnakeHead;
import game.Field;
import game.Game;
import game.Levels;
import gui.CUI;
import utils.Point;

public class Main {
	
	public static boolean equalsDirection(Point firstDirection, Point secondDirection) {
		return firstDirection.x == secondDirection.x && firstDirection.y == secondDirection.y;
	}
	
	public static void changeDirection(String command, SnakeHead snakeHead) {
		Point direction = snakeHead.getDirection();
		if (command.equals("d") && !equalsDirection(direction, new Point(-1, 0))) {
			snakeHead.setDirection(new Point(1, 0));
		}
		else if (command.equals("w") && !equalsDirection(direction, new Point(0, 1))) {
			snakeHead.setDirection(new Point(0, -1));
		}
		else if (command.equals("a") && !equalsDirection(direction, new Point(1, 0))) {
			snakeHead.setDirection(new Point(-1, 0));
		}
		else if (command.equals("s") && !equalsDirection(direction, new Point(0, -1))) {
			snakeHead.setDirection(new Point(0, 1));
		}
	}
	
	public static void main(String[] args) {
		Field field = Levels.Level1.initilize();
		
		Game game = new Game(field);
		SnakeHead snakeHead = game.findSnakeHead();
		Scanner in = new Scanner(System.in);
		while (!game.gameOver) {
			CUI.print(field);
			String command = in.next();	
			changeDirection(command, snakeHead);
			game.tick();
			
			//TimeUnit.MILLISECONDS.sleep(500);
			
		}
	}
}
