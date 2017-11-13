package fieldObjects;

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
	
	public void treatCollision(Game game) {
		game.gameOver = true;
	}
}
