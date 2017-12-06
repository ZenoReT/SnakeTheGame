package fieldObjects;

import game.Game;
import utils.Point;

public class EmptyCell implements FieldObject {
	private Point location;
	
	
	
	public EmptyCell(int x, int y) {
		location = new Point(x, y);
		
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}
	
	public void treatCollisionWithSnake(Game game) {}
	
	public void tick(Game game) {}
}
