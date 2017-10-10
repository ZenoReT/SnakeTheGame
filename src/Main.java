
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		Field field = Levels.Level1.initilizeLevel();
		Game game = new Game();
		while (!game.gameOver) {
			SimpleGui.print(field);
			//TimeUnit.MILLISECONDS.sleep(500);
			game.tick(field);
		}
	}
}
