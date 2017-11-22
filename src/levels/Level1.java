package levels;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import fieldObjects.AcceleratorBonus;
import fieldObjects.FieldObject;
import fieldObjects.ResetAcceleratorBonus;
import game.Field;
import utils.Consts;

public class Level1 extends Level {
	private Field field;
	private int bonusChance = 15;
	private Consts consts = new Consts();
	private Random rnd = new Random();

	public Level1(String level) throws IOException {
		field = Level.loadFile(String.format("src\\levels\\tableLevels\\%s.txt", level));
	}

	public Field getField() {
		return field;
	}

	public void runRules() {
		ArrayList<FieldObject> emptyCells = getEmptyCells();
		int chance = rnd.nextInt(100);
		int cellID = rnd.nextInt(emptyCells.size());
		utils.Point cellLocation = emptyCells.get(cellID).getLocation();
		Object[] bonuses = consts.bonuses.keySet().toArray();
		int bonusID = rnd.nextInt(bonuses.length);
		FieldObject bonus = (FieldObject)bonuses[bonusID];
		if (!consts.bonuses.get(bonus) && bonusChance > chance) {
			if (bonus.getClass() == AcceleratorBonus.class) {
				field.getObjects().add(new AcceleratorBonus(cellLocation.x, cellLocation.y));
				consts.bonuses.put(new AcceleratorBonus(-1, -1), true);					
			}
			else if (bonus.getClass() == ResetAcceleratorBonus.class) {
				field.getObjects().add(new ResetAcceleratorBonus(cellLocation.x, cellLocation.y));
				consts.bonuses.put(new ResetAcceleratorBonus(-1, -1), true);					
			}
		}
	}
}
