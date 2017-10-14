package GUI;

import java.awt.event.KeyEvent;

import FieldObjects.SnakeHead;
import Utils.Point;
public class UserInterface{

	public void keyPressed(KeyEvent e, SnakeHead snakeHead) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			snakeHead.setDirection(new Point(0, -1));
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			 snakeHead.setDirection(new Point(0, 1));
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			 snakeHead.setDirection(new Point(-1, 0));
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			 snakeHead.setDirection(new Point(1, 0));
		}
	}
}
