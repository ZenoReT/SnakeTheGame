package utils;

import java.util.HashMap;

import fieldObjects.AcceleratorBonus;
import fieldObjects.FieldObject;
import fieldObjects.ResetAcceleratorBonus;

public class Consts {
	public final HashMap<FieldObject, Boolean> bonuses;
	
	public Consts(){
		bonuses = new HashMap<FieldObject, Boolean>();
		bonuses.put(new AcceleratorBonus(-1, -1), false);
		bonuses.put(new ResetAcceleratorBonus(-1, -1), false);
	}
}
