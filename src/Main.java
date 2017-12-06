import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

import fieldObjects.SnakeHead;
import game.Field;
import game.Game;
import gui.CUI;
import gui.GUI;
import levels.Level;
import levels.Level1;
import utils.Consts;
import utils.Point;

public class Main {
	
	public static boolean equalsDirection(Point firstDirection, Point secondDirection) {
		return firstDirection.equals(secondDirection);
	}
	
	public static boolean canChangeDirection(SnakeHead snakeHead, Point newDirection) {
		if (snakeHead.getPreviousPart() == null || !equalsDirection(
				snakeHead.getPreviousPart().getLocation().sub(snakeHead.getLocation()),
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

	public static void main(String[] args) throws IOException {
		Map<Integer,Point> wasd = new HashMap<Integer,Point>();
		wasd.put(87,  new Point(0,-1));
		wasd.put(65,  new Point(-1,0));
		wasd.put(83,  new Point(0,1));
		wasd.put(68,  new Point(1,0));
		Level level = new Level1(Consts.levels[new Random().nextInt(Consts.levels.length)]);
		Game game = new Game(level);
		SnakeHead snakeHead = game.findSnakeHead();
		if (args.length == 0) {
			startGui(level.getField(), game, snakeHead, wasd);
		}
		else {
			startCui(level.getField(), game, snakeHead);
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
            	if (wasd.containsKey(e.getKeyCode()) && 
            			canChangeDirection(snakeHead, wasd.get(e.getKeyCode()))) {
            		snakeHead.setDirection(wasd.get(e.getKeyCode()));
            		}
            	}
          });
        frame.add(gui);
        frame.pack();
        frame.setVisible(true);
		frame.setVisible(true);
		while (!game.gameOver) {
			try {
				TimeUnit.MILLISECONDS.sleep(game.getSpeed());
			}
			catch (InterruptedException e) {}
			game.tick();
			gui.onTick = true;
			gui.repaint();
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
