
public class SimpleGui {
	public static void print(Field field) {
		for (int y = 0; y < field.getHeigth(); y++) {
			for (int x = 0; x < field.getWidth(); x++) {
				if (field.field[x][y].getClass() == Apple.class) {
					System.out.print("A");
				}
				else if (field.field[x][y].getClass() == EmptyCell.class) {
					System.out.print(" ");
				}
				if (field.field[x][y].getClass() == SnakeHead.class) {
					System.out.print("3");
				}
				if (field.field[x][y].getClass() == SnakePart.class) {
					System.out.print("O");
				}
				if (field.field[x][y].getClass() == Wall.class) {
					System.out.print("#");
				}
			}
			System.out.println();
		}
	}
}
