package gui;
import fieldObjects.AcceleratorBonus;
import fieldObjects.Apple;
import fieldObjects.EmptyCell;
import fieldObjects.ResetAcceleratorBonus;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import fieldObjects.Wall;
import game.Field;

public class CUI {
	public static void print(Field field) {
		for (int y = 0; y < field.getHeigth(); y++) {
			for (int x = 0; x < field.getWidth(); x++) {
				if (field.getField()[x][y].getClass() == Apple.class) {
					System.out.print("A");
				}
				else if (field.getField()[x][y].getClass() == EmptyCell.class) {
					System.out.print(" ");
				}
				if (field.getField()[x][y].getClass() == SnakeHead.class) {
					System.out.print("3");
				}
				if (field.getField()[x][y].getClass() == SnakePart.class) {
					System.out.print("O");
				}
				if (field.getField()[x][y].getClass() == Wall.class) {
					System.out.print("#");
				}
				if (field.getField()[x][y].getClass() == AcceleratorBonus.class) {
					System.out.print('S');
				}
				if (field.getField()[x][y].getClass() == ResetAcceleratorBonus.class) {
					System.out.print('R');
				}
			}
			System.out.println();
		}
	}
}
