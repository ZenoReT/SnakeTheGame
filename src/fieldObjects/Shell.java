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

	public Point getLocation() {
		return location;
	}

	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	public void treatCollision(Game game) {
		game.gameOver = true;
	}

	public void tick(Game game) {
		FieldObject nextFieldObject = game.getField().getField()[location.x + direction.x][location.y + direction.y];
		SnakeHead snakeHead = game.findSnakeHead();
		if(nextFieldObject.getClass() == SnakeHead.class &&
				snakeHead.getLocation().x == location.x && snakeHead.getLocation().y == location.y) {
			treatCollision(game);
		}
		else if (nextFieldObject.getClass() != EmptyCell.class) {
			Field field = game.getField();
			for (int x = 0; x < field.getObjects().size(); x++) {
				if (field.getObjects().get(x).getClass() == this.getClass()) {
					field.getObjects().remove(field.getObjects().get(x));
					break;
					}
				}
			}
		else {
			setLocation(location.x + direction.x, location.y + direction.y);
		}
	}

	public void generate(Game game) {}
}
