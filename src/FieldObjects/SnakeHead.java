package fieldObjects;
import utils.Point;

public class SnakeHead implements FieldObject {
	private Point location;
	private Point direction = new Point(-1, 0);
	private SnakePart previousPart = null;
	
	public SnakeHead(int x, int y, Point direction) {
		location = new Point(x, y);
		this.direction = direction;
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}

	public boolean isCollisionCapable() {
		return true;
	}
	
	public boolean deadInConflict() {
		return true;
	}
	
	public void setDirection(Point direction) {			
		this.direction = direction;
	}
	
	public Point getDirection() {
		return direction;
	}
	
	public void setPreviousPart(SnakePart snakePart) {
		previousPart = snakePart;
	}
	
	public SnakePart getPreviousPart() {
		return previousPart;
	}
}
