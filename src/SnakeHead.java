import java.util.Arrays;

public class SnakeHead implements FieldObject {
	private Point location;
	private String direction;
	public final String[] directions = new String[] {"Down", "Up", "Left", "Right"};
	public SnakePart previousPart = null;
	
	public SnakeHead(int x, int y) {
		location = new Point(x, y);
		direction = "Up";
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
	
	public void setDirection(String direction) {
		if (Arrays.asList(directions).contains(direction)) {			
			this.direction = direction;
		}
	}
	
	public String getDirection() {
		return direction;
	}
}
