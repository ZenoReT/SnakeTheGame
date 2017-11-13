import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import fieldObjects.SnakeHead;
import game.Field;
import game.Game;
import game.Levels;
import gui.CUI;
import gui.GUI;
import utils.Point;

public class Main {
	
	public static boolean equalsDirection(Point firstDirection, Point secondDirection) {
		return firstDirection.x == secondDirection.x && firstDirection.y == secondDirection.y;
	}
	
	public static boolean canChangeDirection(SnakeHead snakeHead, Point newDirection) {
		if (snakeHead.getPreviousPart() == null || !equalsDirection(
				new Point(snakeHead.getPreviousPart().getLocation().x - snakeHead.getLocation().x,
		                  snakeHead.getPreviousPart().getLocation().y - snakeHead.getLocation().y),
						  newDirection))
			return true;
		return false;
	}

	public static void changeDirection(String command, SnakeHead snakeHead) {
		if (command.equals("d") && canChangeDirection(snakeHead, new Point(1, 0))) {
			snakeHead.setDirection(new Point(1, 0));
		}
		else if (command.equals("w") && canChangeDirection(snakeHead, new Point(0, -1))) {
			snakeHead.setDirection(new Point(0, -1));
		}
		else if (command.equals("a") && canChangeDirection(snakeHead, new Point(-1, 0))) {
			snakeHead.setDirection(new Point(-1, 0));
		}
		else if (command.equals("s") && canChangeDirection(snakeHead, new Point(0, 1))) {
			snakeHead.setDirection(new Point(0, 1));
		}
	}

	public static void main(String[] args) {
		Map<Integer,Point> wasd = new HashMap<Integer,Point>();
		wasd.put(87,  new Point(0,-1));
		wasd.put(65,  new Point(-1,0));
		wasd.put(83,  new Point(0,1));
		wasd.put(68,  new Point(1,0));
		Field field = Levels.Level1.initilize();
		Game game = new Game(field);
		SnakeHead snakeHead = game.findSnakeHead();
		if (args.length == 0) {
			startGui(field, game, snakeHead, wasd);
		}
		else {
			startCui(field, game, snakeHead);
		}
		System.exit(0);
	}
	
	private static void startGui(Field field, Game game, SnakeHead snakeHead, Map<Integer, Point> wasd) {
		int size = 50;
		int height = game.getField().getHeigth();
		int width = game.getField().getWidth();
		GUI gui = new GUI(game, size);
		gui.setPreferredSize(new Dimension(width*size, height*size));

		JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            	//Point reversedDirection = new Point(snakeHead.getDirection().x*(-1),
            	//									snakeHead.getDirection().y*(-1));
            	//if (wasd.containsKey(e.getKeyCode()) && 
            	//		!equalsDirection(wasd.get(e.getKeyCode()), reversedDirection))
            	//	snakeHead.setDirection(wasd.get(e.getKeyCode()));
            	if (wasd.containsKey(e.getKeyCode()) && 
            			canChangeDirection(snakeHead, wasd.get(e.getKeyCode()))) {
            		snakeHead.setDirection(wasd.get(e.getKeyCode()));
            		}
            	}
          });
        frame.add(gui);
        //frame.setPreferredSize(new Dimension(width*size, height*size));
        frame.pack();
        frame.setVisible(true);
        //frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		while (!game.gameOver) {
			try {
				TimeUnit.MILLISECONDS.sleep(game.getSpeed());
			}
			catch (InterruptedException e) {}
			game.tick();
			gui.onTick = true;
			gui.repaint();
			//gui.setVisible(true);
		}
	}
	
	private static void startCui(Field field, Game game, SnakeHead snakeHead) {
		Scanner in = new Scanner(System.in);
		while (!game.gameOver) {
			try {
				TimeUnit.MILLISECONDS.sleep(game.getSpeed());
			} 
			catch (InterruptedException e) {}
			CUI.print(field);
			String command = in.next();	
			changeDirection(command, snakeHead);
			game.tick();
		}
		in.close();
	}
}
