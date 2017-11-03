package fieldObjects;
import java.io.File;

import gui.Animation;
import utils.Point;

public class AcceleratorBonus implements FieldObject {
	private Point location;
	private int lifeTime = 10;
	public final int speedChanger = 50;
	
	public AcceleratorBonus(int x, int y) {
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
	
	public int getLifeTime() {
		return lifeTime;
	}
	
	public void decreaseLifeTime() {
		lifeTime--;
	}
}
