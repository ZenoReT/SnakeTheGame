package fieldObjects;

import java.util.ArrayList;
import java.util.Random;

import game.Field;
import game.Game;
import utils.Point;

public class WallWithTrap implements FieldObject {
	private Point location;
	private int lifeTime = 10;
	
	public WallWithTrap(int x, int y, int lifeTime) {
		location = new Point(x, y);
		if (lifeTime > 0) {
			this.lifeTime = lifeTime;
		}
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}

	public void treatCollision(Game game) {
		game.gameOver = true;
	}
	
	private void decreaseLifeTime() {
		lifeTime--;
	}
	
	private Point getDirectionOfShell(Game game) {
		int lenToNextWall = 0;
		Point direction = new Point(0, 0);
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if(Math.abs(x) != Math.abs(y)) {
					int currentLen = getLenToWallTo(game, new Point(x, y));
					if (currentLen > lenToNextWall) {
						lenToNextWall = currentLen;
						direction = new Point(x, y);
					}
				}
			}
		}
		return direction;
	}
	
	private int getLenToWallTo(Game game, Point direction) {
		FieldObject[][] field = game.getField().getField();
		Point shellLocation = new Point(location.x + direction.x, location.y + direction.y);
		int lenToWall = 0;
		while (game.getField().isOnField(shellLocation.x, shellLocation.y) &&
				field[shellLocation.x][shellLocation.y].getClass() != Wall.class){
			shellLocation = new Point(shellLocation.x + direction.x, shellLocation.y + direction.y);
			lenToWall++;
		}
		return lenToWall;
	}

	public void tick(Game game) {
		if (lifeTime <= 0) {
			Field field = game.getField();
			for (int x = 0; x < field.getObjects().size(); x++) {
				if (field.getObjects().get(x).getClass() == this.getClass()) {
					field.getObjects().remove(field.getObjects().get(x));
					break;
				}
			}
		}
		else if (game.getLevel().getObjectsOf(Shell.class).size() == 0) {
			Point direction = getDirectionOfShell(game);
			if (direction.x != 0 || direction.y != 0) {
				game.getField().getObjects().add(new Shell(
												 location.x + direction.x, location.y + direction.y, direction));
			}
		}
		decreaseLifeTime();
	}

	public void generate(Game game) {
		ArrayList<FieldObject> walls = game.getLevel().getObjectsOf(Wall.class);
		Random rnd = new Random();
		int id = rnd.nextInt(walls.size());
		Point cellLocation = walls.get(id).getLocation();
		game.getField().getObjects().add(new WallWithTrap(cellLocation.x, cellLocation.y, 0));
	}

}
