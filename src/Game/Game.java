package game;
import java.util.ArrayList;

import fieldObjects.EmptyCell;
import fieldObjects.FieldObject;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import levels.Level;
import utils.Point;
import utils.Consts;

public class Game {
	public boolean gameOver = false;
	private Field field;
	private int speed = 2500;
	public Consts consts = new Consts();
	Level level;
			
	public Game(Level level) {
		field = level.getField();
		this.level = level;
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
	
	public Level getLevel() {
		return level;
	}
	
	public void tick() {
		SnakeHead snakeHead = findSnakeHead();
		moveSnake(snakeHead);
		Point headLocation = snakeHead.getLocation();
		FieldObject currentCell = field.getField()[headLocation.x][headLocation.y];
		
		currentCell.treatCollisionWithSnake(this);
		runObjectsTicks();
		level.runRules();
		
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
		SnakeHead snakeHead = null;
		for (int i = 0; i < field.getObjects().size(); i++) {
			if (field.getObjects().get(i) instanceof SnakeHead) {
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
			Point nextCoordinate = new Point(snakeHead.getLocation());
			while (true) {
				Point temp = new Point(currentPart.getLocation());
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

	public static boolean equalsDirection(Point firstDirection, Point secondDirection) {
		return firstDirection.equals(secondDirection);
	}

	public static boolean canChangeDirection(SnakeHead snakeHead, Point newDirection) {
		if (snakeHead.getPreviousPart() == null || !equalsDirection(
				snakeHead.getPreviousPart().getLocation().sub(snakeHead.getLocation()),
						  newDirection))
			return true;
		return false;
	}

	public static void changeDirection(String command, SnakeHead snakeHead) {
		if (command.equals("d") && canChangeDirection(snakeHead, new Point(1, 0))) {
			snakeHead.setDirection(new Point(1, 0));
		}
		else if (command.equals("w") && canChangeDirection(snakeHead, new Point(0, -1))) {
			snakeHead.setDirection(new Point(0, -1));
		}
		else if (command.equals("a") && canChangeDirection(snakeHead, new Point(-1, 0))) {
			snakeHead.setDirection(new Point(-1, 0));
		}
		else if (command.equals("s") && canChangeDirection(snakeHead, new Point(0, 1))) {
			snakeHead.setDirection(new Point(0, 1));
		}
	}
}
