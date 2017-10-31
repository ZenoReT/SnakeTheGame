package fieldObjects;
import java.io.File;

import gui.Animation;
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

	public boolean isCollisionCapable() {
		return true;
	}
	
	public boolean deadInConflict() {
		return true;
	}
	
	
}
