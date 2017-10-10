
import java.awt.event.KeyEvent;
public class UserInterface{

	public void keyPressed(KeyEvent e, SnakeHead snakeHead) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			snakeHead.setDirection("Up");
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			 snakeHead.setDirection("Down");
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			 snakeHead.setDirection("Left");			
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			 snakeHead.setDirection("Right");			
		}
	}
}
