package GUI;
import FieldObjects.Apple;
import FieldObjects.EmptyCell;
import FieldObjects.SnakeHead;
import FieldObjects.SnakePart;
import FieldObjects.Wall;
import Game.Field;

public class SimpleGui {
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
			}
			System.out.println();
		}
	}
}
