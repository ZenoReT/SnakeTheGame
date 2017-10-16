package fieldObjects;
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

	public boolean isCollisionCapable() {
		return false;
	}
	
	public boolean deadInConflict() {
		return false;
	}

}
