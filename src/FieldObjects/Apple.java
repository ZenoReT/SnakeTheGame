package fieldObjects;
import java.io.File;

import gui.Animation;
import utils.Point;

public class Apple implements FieldObject {
	private Point location;
	public Animation animation;
	
	public Apple(int x, int y) {
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
		return false;
	}
	
	
}
