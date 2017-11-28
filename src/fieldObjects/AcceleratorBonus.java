package fieldObjects;

import java.util.ArrayList;
import java.util.Random;

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
	
	public void treatCollision(Game game) {
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
		if (lifeTime <= 0){
			Field field = game.getField();
			for (int x = 0; x < field.getObjects().size(); x++) {
				if (field.getObjects().get(x).getClass() == this.getClass()) {
					field.getObjects().remove(field.getObjects().get(x));
					break;
				}
			}
		}
	}
	
	public void generate(Game game){
		ArrayList<FieldObject> emptyCells = game.getLevel().getEmptyCells();
		Random rnd = new Random();
		int id = rnd.nextInt(emptyCells.size());
		Point cellLocation = emptyCells.get(id).getLocation();
		game.getField().getObjects().add(new AcceleratorBonus(cellLocation.x, cellLocation.y, 0, 0));
	}
}
