package levels;

import game.Field;

public class TestLevel extends Level {
	private Field field;

	public Field getField() {
		return field;
	}
	
	public void setField(Field field) {
		this.field = field;
	}

	public void runRules() {}

}
