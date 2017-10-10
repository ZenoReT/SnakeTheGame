
public class Levels {
	
	public static class Level1{
		public static Field initilizeLevel() {
			Field field = new Field(10, 10);
			SnakeHead snakeHead = new SnakeHead(5, 6);
			SnakePart firstSnakePart = new SnakePart(5, 7);
			SnakePart secondSnakePart = new SnakePart(5, 8);
			snakeHead.previousPart = firstSnakePart;
			firstSnakePart.previousPart = secondSnakePart;
			field.objects.add(snakeHead);
			field.objects.add(firstSnakePart);
			field.objects.add(secondSnakePart);
			field.objects.add(new Apple(5, 4));
			for (int x = 0; x < field.getWidth(); x++) {
				for (int y = 0; y < field.getHeigth(); y++) {
					if (x == 0 || x == field.getWidth() - 1 ||
						y == 0 || y == field.getWidth() - 1	) {
						field.objects.add(new Wall(x, y));
					}
				}
			}
			field.initilizeField();
			return field;
		}
	}
}
