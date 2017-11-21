package game;
import fieldObjects.*;
import utils.Point;
import java.util.Random;

public class Levels {
    public static class Level1{
        public static Point getRandomFieldSize() {
            Random random = new Random();
            int randx = 10 + random.nextInt(5);
            int randy = 10 + random.nextInt(15);
            return new Point(Math.max(randx, randy),
                    Math.min(randx, randy));
        }

		public static Field initilize() {
		    Point fieldSize = getRandomFieldSize();
			Field field = new Field(fieldSize.x, fieldSize.y);
			SnakeHead snakeHead = new SnakeHead(1, fieldSize.y - 3, new Point(0, -1));
			SnakePart firstSnakePart = new SnakePart(1, fieldSize.y - 2);
			SnakePart secondSnakePart = new SnakePart(1, fieldSize.y - 1);
			snakeHead.setPreviousPart(firstSnakePart);
			firstSnakePart.setPreviousPart(secondSnakePart);
			field.getObjects().add(snakeHead);
			field.getObjects().add(firstSnakePart);
			field.getObjects().add(secondSnakePart);
			field.getObjects().add(new Apple(5, 4));
			for (int x = 0; x < field.getWidth(); x++) {
				for (int y = 0; y < field.getHeigth(); y++) {
					if (x == 0 || x == field.getWidth() - 1 ||
						y == 0 || y == field.getHeigth() - 1	) {
						field.getObjects().add(new Wall(x, y));
					}
				}
			}
            field.initilizeField();
            return field;
		}
	}
}
