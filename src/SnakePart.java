
public class SnakePart implements FieldObject {
	private Point location;
	public SnakePart previousPart = null;
	
	public SnakePart(int x, int y) {
		location = new Point(x, y);
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}

	public boolean isWalkable() {
		return false;
	}

}
