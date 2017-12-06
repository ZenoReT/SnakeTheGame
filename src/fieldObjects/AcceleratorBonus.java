package fieldObjects;

import game.Field;
import game.Game;
import utils.Point;

public class AcceleratorBonus implements FieldObject {
	private Point location;
	private int lifeTime = 5;
	private int speedChanger = 50;
	
	public AcceleratorBonus(int x, int y, int lifeTime, int speedChanger) {
		location = new Point(x, y);
		if (lifeTime > 0){
			this.lifeTime = lifeTime;
		}
		if (speedChanger > 0) {
			this.speedChanger = speedChanger;			
		}
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
	
	public void treatCollisionWithSnake(Game game) {
		Field field = game.getField();
		SnakeHead snakeHead = game.findSnakeHead(); 
		Point headLocation = snakeHead.getLocation();
		FieldObject bonus = field.getField()[headLocation.x][headLocation.y];
		int currentSpeed = game.getSpeed();
		if (currentSpeed > 100) {
			game.setSpeed(currentSpeed - speedChanger);
		}
		field.getObjects().remove(bonus);
	}

	public void tick(Game game) {
		decreaseLifeTime();
		if (lifeTime <= 0) {
			Field field = game.getField();
			field.getObjects().remove(this);
		}
	}
}
