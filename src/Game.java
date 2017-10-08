
public class Game {
	public boolean gameOver = false;
	
	public void tick(Field field) {
		while (!gameOver){
			moveSnake(field);
		}
		System.out.println("Game over!");
	}
	
	public void moveSnake(Field field) {
		Object classOfHead = new SnakeHead(0, 0).getClass();
		SnakeHead snakeHead = null;
		for (int i = 0; i < field.objects.size(); i++) {
			if (field.objects.get(i).getClass() == classOfHead) {
				snakeHead = field.objects.get(i);
				break;
			}
		}
		
	}
}
