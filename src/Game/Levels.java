package game;
import fieldObjects.Apple;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import fieldObjects.Wall;
import utils.Point;

public class Levels {
	
	public static class Level1{
		public static Field initilize() {
			Field field = new Field(15, 15);
			SnakeHead snakeHead = new SnakeHead(5, 6, new Point(0, -1));
			SnakePart firstSnakePart = new SnakePart(5, 7);
			SnakePart secondSnakePart = new SnakePart(5, 8);
			snakeHead.setPreviousPart(firstSnakePart);
			firstSnakePart.setPreviousPart(secondSnakePart);
			field.getObjects().add(snakeHead);
			field.getObjects().add(firstSnakePart);
			field.getObjects().add(secondSnakePart);
			field.getObjects().add(new Apple(5, 4));
			for (int x = 0; x < field.getWidth(); x++) {
				for (int y = 0; y < field.getHeigth(); y++) {
					if (x == 0 || x == field.getWidth() - 1 ||
						y == 0 || y == field.getWidth() - 1	) {
						field.getObjects().add(new Wall(x, y));
					}
				}
			}
			field.initilizeField();
			return field;
		}
	}
}
