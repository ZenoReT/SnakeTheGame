
import java.util.Random;

public class Game {
	private boolean gameOver = false;
	
	public void tick(Field field) {
		while (!gameOver){
			SnakeHead snakeHead = findSnakeHead(field);
			moveSnake(snakeHead);
			if (isInterrupt(field, snakeHead)){
				treatInterruption(field, snakeHead);
			}
			initilizeField(field);
		}
		System.out.println("Game over!");
	}
	
	private SnakeHead findSnakeHead(Field field) {
		Object classOfHead = SnakeHead.class;
		SnakeHead snakeHead = null;
		for (int i = 0; i < field.objects.size(); i++) {
			if (field.objects.get(i).getClass() == classOfHead) {
				snakeHead = (SnakeHead)field.objects.get(i);
				break;
			}
		}
		return snakeHead;
	}
	
	private void moveSnake(SnakeHead snakeHead) {
		if (snakeHead.previousPart != null)
		{
			SnakePart currentPart = snakeHead.previousPart;
			Point nextCoordinate = new Point(snakeHead.getLocation().x,
											 snakeHead.getLocation().y);
			while (true) {
				currentPart.setLocation(nextCoordinate.x, nextCoordinate.y);
				currentPart = currentPart.previousPart;
				if (currentPart == null) {
					break;
				}
				nextCoordinate = new Point(currentPart.getLocation().x,
										   currentPart.getLocation().y);
			}
		}
		if (snakeHead.getDirection() == "Up") {
			snakeHead.setLocation(snakeHead.getLocation().x,
								  snakeHead.getLocation().y - 1);
		}
		else if (snakeHead.getDirection() == "Down") {
			snakeHead.setLocation(snakeHead.getLocation().x,
					  			  snakeHead.getLocation().y + 1);
		}
		else if (snakeHead.getDirection() == "Left") {
			snakeHead.setLocation(snakeHead.getLocation().x - 1,
					              snakeHead.getLocation().y);
		}
		else if (snakeHead.getDirection() == "Right") {
			snakeHead.setLocation(snakeHead.getLocation().x + 1,
					              snakeHead.getLocation().y);
		}
	}
	
	private boolean isInterrupt(Field field, SnakeHead snakeHead) {
		Point headLocation = new Point(snakeHead.getLocation().x,
								   snakeHead.getLocation().y);
		return !field.field[headLocation.x][headLocation.y].isWalkable();
	}
	
	private void treatInterruption(Field field, SnakeHead snakeHead) {
		Point headLocation = new Point(snakeHead.getLocation().x,
				                   snakeHead.getLocation().y);
		Object cellClass = field.field[headLocation.x][headLocation.y];
		if (cellClass == SnakePart.class || cellClass == Wall.class)
		{
			gameOver = true;
		}
		else if (cellClass == Apple.class) {
			if (snakeHead.previousPart == null) {
				snakeHead.previousPart = new SnakePart(headLocation.x,
													   headLocation.y);
				field.objects.add(snakeHead.previousPart);
			}
			else {
				SnakePart currentPart = snakeHead.previousPart;
				while (currentPart.previousPart != null) {
					currentPart = currentPart.previousPart;
				}
				currentPart.previousPart = new SnakePart(currentPart.getLocation().x,
														 currentPart.getLocation().y);
				field.objects.add(currentPart.previousPart);
			}
			field.objects.remove(field.field[headLocation.x][headLocation.y]);
			appleGenerator(field, snakeHead);
		}
	}
	
	private void appleGenerator(Field field, SnakeHead snakeHead){
		Random rand = new Random();
		boolean haveApple = false;
		while (!haveApple){
			int x = rand.nextInt(field.getWidth());
			int y = rand.nextInt(field.getHeigth());
			if (field.field[x][y].getClass() == EmptyCell.class &&
				(x != snakeHead.getLocation().x || y != snakeHead.getLocation().y)){
				haveApple = true;
				field.objects.add(new Apple(x, y));
			}
		}
	}
	
	public static void initilizeField(Field field) {
		for (int i = 0; i < field.objects.size(); i++){
			int x = field.objects.get(i).getLocation().x;
			int y = field.objects.get(i).getLocation().y;
			field.field[x][y] = field.objects.get(i);
		}
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getHeigth(); y++) {
				if (field.field[x][y] == null) {
					field.field[x][y] = new EmptyCell(x, y);
				}
			}
		}
	}
}
