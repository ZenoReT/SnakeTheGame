package fieldObjects;
import java.io.File;

import gui.Animation;
import utils.Point;

public class SnakeHead implements FieldObject {
	private Point location;
	private Point direction = new Point(-1, 0);
	private SnakePart previousPart = null;
	public Animation animation;
	
	public SnakeHead(int x, int y, Point direction) {
		location = new Point(x, y);
		this.direction = direction;
		animation = new Animation(new File("Animations\\Head"), 1);
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
	
	public void setDirection(Point direction) {			
		this.direction = direction;
	}
	
	public Point getDirection() {
		return direction;
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
