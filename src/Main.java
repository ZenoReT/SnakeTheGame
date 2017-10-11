import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	
	public static void main(String[] args) {
		Field field = Levels.Level1.initilizeLevel();
		
		Game game = new Game();
		SnakeHead head = game.findSnakeHead(field);
		Scanner in = new Scanner(System.in);
		while (!game.gameOver) {
			SimpleGui.print(field);
			String command = in.next();	
			game.changeDirection(command, head);
			game.tick(field);
			
			//TimeUnit.MILLISECONDS.sleep(500);
			
		}
	}
}
