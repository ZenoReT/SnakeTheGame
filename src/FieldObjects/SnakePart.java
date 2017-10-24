package fieldObjects;
import java.io.File;

import gui.Animation;
import utils.Point;

public class SnakePart implements FieldObject {
	private Point location;
	private SnakePart previousPart = null;
	public Animation animation;
	
	public SnakePart(int x, int y) {
		location = new Point(x, y);
		animation = new Animation(new File("Animations\\Body"), 1);
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
	
	public void setPreviousPart(SnakePart snakePart) {
		previousPart = snakePart;
	}
	
	public SnakePart getPreviousPart() {
		return previousPart;
	}
	
	public Animation getAnimation() {
		return this.animation;
	}
}
