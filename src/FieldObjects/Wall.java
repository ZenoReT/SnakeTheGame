package fieldObjects;

import java.util.ArrayList;
import java.util.Random;

import game.Game;
import utils.Point;

public class Wall implements FieldObject {
	private Point location;
	
	
	public Wall(int x, int y) {
		location = new Point(x, y);
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}
	
	public void treatCollisionWithSnake(Game game) {
		game.gameOver = true;
	}
	
	public void tick(Game game) {}
}
