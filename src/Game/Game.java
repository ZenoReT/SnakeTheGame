package Game;
import java.util.ArrayList;
import java.util.Random;

import FieldObjects.Apple;
import FieldObjects.EmptyCell;
import FieldObjects.FieldObject;
import FieldObjects.SnakeHead;
import FieldObjects.SnakePart;
import Utils.Point;

public class Game {
	public boolean gameOver = false;
	private Field field = null;
			
	public Game(Field field) {
		this.field = field;
	}
	
	public Field getField() {
		return field;
	}
	
	public void tick() {
		SnakeHead snakeHead = findSnakeHead();
		moveSnake(snakeHead);
		if (isCollision(snakeHead)) {
			treatCollision(snakeHead);
		}
		field.initilizeField();
	}
	
	public SnakeHead findSnakeHead() {
		Object classOfHead = SnakeHead.class;
		SnakeHead snakeHead = null;
		for (int i = 0; i < field.getObjects().size(); i++) {
			if (field.getObjects().get(i).getClass() == classOfHead) {
				snakeHead = (SnakeHead)field.getObjects().get(i);
				break;
			}
		}
		return snakeHead;
	}
	
	public FieldObject findSnakeTail(SnakeHead snakeHead) {
		if (snakeHead.getPreviousPart() == null) {
			return snakeHead;
		}
		SnakePart currentPart = snakeHead.getPreviousPart();
		while (currentPart.getPreviousPart() != null) {
			currentPart = currentPart.getPreviousPart();
		}
		return currentPart;
	}
	
	private void moveSnake(SnakeHead snakeHead) {
		if (snakeHead.getPreviousPart() != null)
		{
			SnakePart currentPart = snakeHead.getPreviousPart();
			Point nextCoordinate = new Point(snakeHead.getLocation().x,
											 snakeHead.getLocation().y);
			while (true) {
				Point temp = new Point(currentPart.getLocation().x, currentPart.getLocation().y);
				currentPart.setLocation(nextCoordinate.x, nextCoordinate.y);
				currentPart = currentPart.getPreviousPart();
				if (currentPart == null) {
					break;
				}
				nextCoordinate = temp;
			}
		}
		snakeHead.setLocation(snakeHead.getLocation().x + snakeHead.getDirection().x,
						      snakeHead.getLocation().y + snakeHead.getDirection().y);
	}
	
	private boolean isCollision(SnakeHead snakeHead) {
		Point headLocation = new Point(snakeHead.getLocation().x,
								   	   snakeHead.getLocation().y);
		FieldObject cellObject = field.getField()[headLocation.x][headLocation.y];
		return cellObject.isCollisionCapable() && !cellObject.equals(findSnakeTail(snakeHead));
	}
	
	private void treatCollision(SnakeHead snakeHead) {
		Point headLocation = new Point(snakeHead.getLocation().x,
				                   snakeHead.getLocation().y);
		FieldObject cellObject = field.getField()[headLocation.x][headLocation.y];
		if (cellObject.deadInConflict()){
			gameOver = true;
		}
		else {
			Object tailCell = findSnakeTail(snakeHead);
			if (tailCell.getClass() == SnakeHead.class) {
				SnakeHead snakeTail = (SnakeHead)tailCell;
				snakeTail.setPreviousPart(new SnakePart(snakeTail.getLocation().x,
                                                        snakeTail.getLocation().y));
				field.getObjects().add(snakeTail.getPreviousPart());
			}
			else {
				SnakePart snakeTail = (SnakePart)tailCell;
				snakeTail.setPreviousPart(new SnakePart(snakeTail.getLocation().x,
                                                        snakeTail.getLocation().y));
				field.getObjects().add(snakeTail.getPreviousPart());
				snakeTail = (SnakePart)snakeTail;
			}
			field.getObjects().remove(field.getField()[headLocation.x][headLocation.y]);
			appleGenerator(snakeHead);
		}
	}
	
	private ArrayList<FieldObject> getEmptyCells(){
		Object emptyCellClass = EmptyCell.class;
		ArrayList<FieldObject> emptyCells = new ArrayList<FieldObject>();
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getHeigth(); y++) {
				if (field.getField()[x][y].getClass() == emptyCellClass) {
					emptyCells.add(field.getField()[x][y]);
				}
			}
		}
		return emptyCells;
	}
	
	private void appleGenerator(SnakeHead snakeHead){
		Random rand = new Random();
		ArrayList<FieldObject> emptyCells = getEmptyCells();
		int id = rand.nextInt(emptyCells.size());
		field.getObjects().add(new Apple(emptyCells.get(id).getLocation().x,
									emptyCells.get(id).getLocation().y));
	}
}
