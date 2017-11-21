package fieldObjects;

import game.Game;
import utils.Point;

public interface FieldObject {
	
	public void setLocation(int x, int y);
	public Point getLocation();
	public void treatCollision(Game game);
	public void tick(Game game);
	public void generate(Game game);
}
