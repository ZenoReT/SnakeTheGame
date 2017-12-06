package fieldObjects;

import game.Field;
import game.Game;
import utils.Point;

public class Shell implements FieldObject {
	private Point direction;
	private Point location;

	public Shell(int x, int y, Point direction) {
		location = new Point(x, y);
		this.direction = direction;
	}
	
	public Point getDirection() {
		return direction;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public void treatCollisionWithSnake(Game game) {
		game.gameOver = true;
	}

	public void tick(Game game) {
		FieldObject nextFieldObject = game.getField().getField()[location.x + direction.x][location.y + direction.y];
		SnakeHead snakeHead = game.findSnakeHead();
		if(nextFieldObject instanceof SnakeHead &&
				snakeHead.getLocation().equals(location)) {
			treatCollisionWithSnake(game);
		}
		else if (!(nextFieldObject instanceof EmptyCell)) {
			Field field = game.getField();
			field.getObjects().remove(this);
		}
		else {
			setLocation(location.x + direction.x, location.y + direction.y);
		}
	}
}
