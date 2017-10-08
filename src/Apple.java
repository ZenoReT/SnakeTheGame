
public class Apple implements FieldObject {
	private Point location;
	
	public Apple(int x, int y) {
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
