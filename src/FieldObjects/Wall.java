package fieldObjects;
import java.io.File;

import gui.Animation;
import utils.Point;

public class Wall implements FieldObject {
	private Point location;
	public Animation animation;
	
	public Wall(int x, int y) {
		location = new Point(x, y);
		animation = new Animation(new File("Animations\\Wall"), 1);
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
	
	public Animation getAnimation() {
		return this.animation;
	}

}
