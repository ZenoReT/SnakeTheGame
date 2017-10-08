
public class Levels {
	public void initilizeField(Field field) {
		for (int i = 0; i < field.objects.size(); i++) {
			FieldObject currentObject = field.objects.get(i);
			int x = currentObject.getLocation().x;
			int y = currentObject.getLocation().y;
			field.field[x][y] = currentObject;
		}
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getHeigth(); y++) {
				if (field.field[x][y] == null) {
					field.field[x][y] = new EmptyCell(x, y);
				}
			}
		}
	}
	
	public class Level1{
		public void initilizeLevel() {
			Field field = new Field(15, 15);
			SnakeHead snakeHead = new SnakeHead(5, 10);
			SnakePart firstSnakePart = new SnakePart(5, 11);
			SnakePart secondSnakePart = new SnakePart(5, 12);
			snakeHead.previousPart = firstSnakePart;
			firstSnakePart.previousPart = secondSnakePart;
			field.objects.add(snakeHead);
			field.objects.add(firstSnakePart);
			field.objects.add(secondSnakePart);
			field.objects.add(new Apple(7, 7));
			for (int x = 0; x < field.getWidth(); x++) {
				for (int y = 0; y < field.getHeigth(); y++) {
					if (x == 0 || x == field.getWidth() - 1 ||
						y == 0 || y == field.getWidth() - 1	) {
						field.objects.add(new Wall(x, y));
					}
				}
			}
			initilizeField(field);
		}
	}
}
