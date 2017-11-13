package fieldObjects;

import game.Game;

public interface FieldBonuses extends FieldObject {
	public int getLifeTime();
	public int getBonusChance();
	public void decreaseLifeTime();
	public void treatCollision(Game game);
}
