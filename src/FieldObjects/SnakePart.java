package fieldObjects;

import game.Game;
import utils.Point;

public class SnakePart implements FieldObject {
	private Point location;
	private SnakePart previousPart = null;
	
	
	public SnakePart(int x, int y) {
		location = new Point(x, y);
		
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}
	
	public void setPreviousPart(SnakePart snakePart) {
		previousPart = snakePart;
	}
	
	public SnakePart getPreviousPart() {
		return previousPart;
	}
	
	public void treatCollision(Game game) {
		if (getPreviousPart() != null) {
			game.gameOver = true;
		}
	}
}
