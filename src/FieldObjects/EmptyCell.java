package fieldObjects;
import java.io.File;

import gui.Animation;
import utils.Point;

public class EmptyCell implements FieldObject {
	private Point location;
	
	public Animation animation;
	
	public EmptyCell(int x, int y) {
		location = new Point(x, y);
		animation = new Animation(new File("Animations\\Empty"), 1);
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
	
	public Animation getAnimation() {
		return animation;
	}
}
