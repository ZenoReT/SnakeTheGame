package utils;

import java.util.HashMap;

import fieldObjects.AcceleratorBonus;
import fieldObjects.FieldBonuses;
import fieldObjects.ResetAcceleratorBonus;

public class Consts {
	public final HashMap<FieldBonuses, Boolean> bonuses;
	
	public Consts(){
		bonuses = new HashMap<FieldBonuses, Boolean>();
		bonuses.put(new AcceleratorBonus(-1, -1), false);
		bonuses.put(new ResetAcceleratorBonus(-1, -1), false);
	}
}
