package game;
import java.util.ArrayList;
import java.util.Random;

import fieldObjects.AcceleratorBonus;
import fieldObjects.Apple;
import fieldObjects.EmptyCell;
import fieldObjects.FieldBonuses;
import fieldObjects.FieldObject;
import fieldObjects.ResetAcceleratorBonus;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import utils.Point;
import utils.Consts;

public class Game {
	public boolean gameOver = false;
	private Field field;
	private int speed = 500;
	private Random rnd = new Random();
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
		treatBonuses();
		
		field.initilizeField();
	}
	
	private void treatBonuses() {
		int chance = 0;
		for (FieldBonuses bonus: consts.bonuses.keySet()){
			chance = rnd.nextInt(100);
			if (bonus.getBonusChance() >= chance && !consts.bonuses.get(bonus)){
				objectGenerator(bonus.getClass());
				consts.bonuses.put(bonus, true);
			}
		}
		for (FieldBonuses fieldBonus: getBonuses()) {
			Object bonusClass = fieldBonus.getClass();
			if(fieldBonus.getLifeTime() > 0) {
				fieldBonus.decreaseLifeTime();
			}
			else {
				field.getObjects().remove(fieldBonus);
				if (bonusClass == AcceleratorBonus.class) {
					consts.bonuses.put(new AcceleratorBonus(-1, -1), false);
				}
				else if (bonusClass == ResetAcceleratorBonus.class) {
					consts.bonuses.put(new ResetAcceleratorBonus(-1, -1), false);
				}
			}
		}
	}
	
	private ArrayList<FieldBonuses> getBonuses(){
		ArrayList<FieldBonuses> bonuses = new ArrayList<FieldBonuses>();
		for (int i = 0; i < field.getObjects().size(); i++) {
			Object currentObject = field.getObjects().get(i);
			if (FieldBonuses.class.isAssignableFrom(currentObject.getClass())) {
				bonuses.add((FieldBonuses)currentObject);
			}
		}
		return bonuses;
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
	
	private ArrayList<FieldObject> getEmptyCells(){
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
	
	public void objectGenerator(Class objectClass){
		ArrayList<FieldObject> emptyCells = getEmptyCells();
		int id = rnd.nextInt(emptyCells.size());
		Point cellLocation = emptyCells.get(id).getLocation();
		if (objectClass == Apple.class) {
			field.getObjects().add(new Apple(cellLocation.x, cellLocation.y));
		}
		else if (objectClass == AcceleratorBonus.class) {
			field.getObjects().add(new AcceleratorBonus(cellLocation.x, cellLocation.y));
		}
		else if (objectClass == ResetAcceleratorBonus.class) {
			field.getObjects().add(new ResetAcceleratorBonus(cellLocation.x, cellLocation.y));
		}
	}
}
