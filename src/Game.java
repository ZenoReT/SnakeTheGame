
import java.util.ArrayList;
import java.util.Random;

public class Game {
	public boolean gameOver = false;
	
	public void changeDirection(String command, SnakeHead head) {
		String direction = head.getDirection();
		if (command.equals("d") & !direction.equals("Left") ) {
			head.setDirection("Right");
		}
		else if (command.equals("w")& !direction.equals("Down") ) {
			head.setDirection("Up");
		}
		else if (command.equals("a")& !direction.equals("Right") ) {
			head.setDirection("Left");
		}
		else if (command.equals("s")& !direction.equals("Up") ) {
			head.setDirection("Down");
		}
	}
	
	public void tick(Field field){
		SnakeHead snakeHead = findSnakeHead(field);
		moveSnake(snakeHead);
		if (isInterrupt(field, snakeHead)){
			treatInterruption(field, snakeHead);
		}
		field.initilizeField();
	}
	
	public SnakeHead findSnakeHead(Field field) {
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
				Point temp = new Point(currentPart.getLocation().x, currentPart.getLocation().y);
				currentPart.setLocation(nextCoordinate.x, nextCoordinate.y);
				currentPart = currentPart.previousPart;
				if (currentPart == null) {
					break;
				}
				nextCoordinate = temp;
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
		Object cellClass = field.field[headLocation.x][headLocation.y].getClass();
		if (cellClass == SnakePart.class || cellClass == Wall.class || cellClass == SnakeHead.class)
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
	
	private ArrayList<FieldObject> getEmptyCells(Field field){
		Object emptyCellClass = EmptyCell.class;
		ArrayList<FieldObject> emptyCells = new ArrayList<FieldObject>();
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getHeigth(); y++) {
				if (field.field[x][y].getClass() == emptyCellClass) {
					emptyCells.add(field.field[x][y]);
				}
			}
		}
		return emptyCells;
	}
	
	private void appleGenerator(Field field, SnakeHead snakeHead){
		Random rand = new Random();
		ArrayList<FieldObject> emptyCells = getEmptyCells(field);
		int id = rand.nextInt(emptyCells.size());
		field.objects.add(new Apple(emptyCells.get(id).getLocation().x,
									emptyCells.get(id).getLocation().y));
	}
}
