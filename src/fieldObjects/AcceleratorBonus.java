package fieldObjects;

import java.util.ArrayList;
import java.util.Random;

import game.Field;
import game.Game;
import utils.Point;

public class AcceleratorBonus implements FieldObject {
	private Point location;
	private int lifeTime = 10;
	public static final int speedChanger = 50;
	public static final int bonusChance = 5;
	
	public AcceleratorBonus(int x, int y) {
		location = new Point(x, y);
			
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}
	
	public int getLifeTime() {
		return lifeTime;
	}
	
	public int getBonusChance() {
		return lifeTime;
	}
	
	private void decreaseLifeTime() {
		lifeTime--;
	}
	
	public void treatCollision(Game game) {
		Field field = game.getField();
		SnakeHead snakeHead = game.findSnakeHead(); 
		Point headLocation = snakeHead.getLocation();
		Object bonus = field.getField()[headLocation.x][headLocation.y];
		int currentSpeed = game.getSpeed();
		if (currentSpeed > 100) {
			game.setSpeed(currentSpeed - speedChanger);
		}
		field.getObjects().remove(bonus);
		game.consts.bonuses.put(new AcceleratorBonus(-1, -1), false);
	}
	
	public void tick(Game game) {
		decreaseLifeTime();
		if (lifeTime == 0){
			Field field = game.getField();
			SnakeHead snakeHead = game.findSnakeHead(); 
			Point headLocation = snakeHead.getLocation();
			Object bonus = field.getField()[headLocation.x][headLocation.y];
			field.getObjects().remove(bonus);
			game.consts.bonuses.put(new AcceleratorBonus(-1, -1), false);
		}
	}
	
	public void generate(Game game){
		ArrayList<FieldObject> emptyCells = game.getEmptyCells();
		Random rnd = new Random();
		int id = rnd.nextInt(emptyCells.size());
		Point cellLocation = emptyCells.get(id).getLocation();
		game.getField().getObjects().add(new AcceleratorBonus(cellLocation.x, cellLocation.y));
	}
}
