package game;
import java.util.ArrayList;

import fieldObjects.EmptyCell;
import fieldObjects.FieldObject;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import utils.Point;
import utils.Consts;

public class Game {
	public boolean gameOver = false;
	private Field field;
	private int speed = 500;
	public Consts consts = new Consts();
			
	public Game(Field field) {
		this.field = field;
	}
	
	public Field getField() {
		return field;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void tick() {
		SnakeHead snakeHead = findSnakeHead();
		moveSnake(snakeHead);
		Point headLocation = snakeHead.getLocation();
		FieldObject currentCell = field.getField()[headLocation.x][headLocation.y];
		
		currentCell.treatCollision(this);
		runObjectsTicks();
		
		field.initilizeField();
	}
	
	public void runObjectsTicks() {
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getHeigth(); y++) {
				field.getField()[x][y].tick(this);
			}
		}
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
	
	public ArrayList<FieldObject> getEmptyCells(){
		Object emptyCellClass = EmptyCell.class;
		ArrayList<FieldObject> emptyCells = new ArrayList<FieldObject>();
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getHeigth(); y++) {
				if (field.getField()[x][y].getClass() == emptyCellClass &&
						!field.getObjects().contains(field.getField()[x][y])) {
					emptyCells.add(field.getField()[x][y]);
				}
			}
		}
		return emptyCells;
	}
}
