package levels;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import fieldObjects.AcceleratorBonus;
import fieldObjects.FieldObject;
import fieldObjects.ResetAcceleratorBonus;
import fieldObjects.Wall;
import fieldObjects.WallWithTrap;
import game.Field;
import utils.Consts;

public class Level1 extends Level {
	private Field field;
	private int bonusChance = 70;
	private Random rnd = new Random();

	public Level1(String level) throws IOException {
		field = Level.loadFile(String.format("src\\levels\\tableLevels\\%s.txt", level));
	}

	public Field getField() {
		return field;
	}
	
	public void setField(Field field) {
		this.field = field;
	}

	public void runRules() {
		ArrayList<FieldObject> emptyCells = getEmptyCells();
		ArrayList<FieldObject> walls = getObjectsOf(Wall.class);
		int chance = rnd.nextInt(100);
		int cellID = rnd.nextInt(emptyCells.size());
		int wallID = rnd.nextInt(walls.size());
		utils.Point cellLocation = emptyCells.get(cellID).getLocation();
		utils.Point wallLocation = walls.get(wallID).getLocation();
		int bonusID = rnd.nextInt(3);
		if (bonusChance > chance) {
			if (bonusID == 0 && getObjectsOf(AcceleratorBonus.class).size() == 0) {
				field.getObjects().add(new AcceleratorBonus(cellLocation.x, cellLocation.y, 10, 0));
			}
			else if (bonusID == 1 && getObjectsOf(ResetAcceleratorBonus.class).size() == 0) {
				field.getObjects().add(new ResetAcceleratorBonus(cellLocation.x, cellLocation.y, 10, 0));
			}
			else if (bonusID == 2 && getObjectsOf(WallWithTrap.class).size() == 0) {
				field.getObjects().add(new WallWithTrap(wallLocation.x, wallLocation.y, 0));
			}
		}
	}
}
