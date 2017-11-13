package fieldObjects;

import game.Field;
import game.Game;
import utils.Point;

public class ResetAcceleratorBonus implements FieldBonuses {
	private Point location;
	private int lifeTime = 10;
	public static final int speedChanger = 500;
	public static final int bonusChance = 1;
	
	public ResetAcceleratorBonus(int x, int y) {
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
	
	public void decreaseLifeTime() {
		lifeTime--;
	}
	
	public void treatCollision(Game game) {
		Field field = game.getField();
		SnakeHead snakeHead = game.findSnakeHead(); 
		Point headLocation = snakeHead.getLocation();
		Object bonus = field.getField()[headLocation.x][headLocation.y];
		game.setSpeed(speedChanger);
		field.getObjects().remove(bonus);
		game.consts.bonuses.put(new ResetAcceleratorBonus(-1, -1), false);
	}
}
