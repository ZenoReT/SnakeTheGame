import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

	public static void changeDirection(String command, SnakeHead snakeHead) {
		Point direction = snakeHead.getDirection();
		if (command.equals("d") && !equalsDirection(direction, new Point(-1, 0))) {
			snakeHead.setDirection(new Point(1, 0));
		}
		else if (command.equals("w") && !equalsDirection(direction, new Point(0, 1))) {
			snakeHead.setDirection(new Point(0, -1));
		}
		else if (command.equals("a") && !equalsDirection(direction, new Point(1, 0))) {
			snakeHead.setDirection(new Point(-1, 0));
		}
		else if (command.equals("s") && !equalsDirection(direction, new Point(0, -1))) {
			snakeHead.setDirection(new Point(0, 1));
		}
	}

	public static void main(String[] args) {
		Field field = Levels.Level1.initilize();
		Map<Integer,Point> wasd = new HashMap<Integer,Point>();
		wasd.put(87,  new Point(0,-1));
		wasd.put(65,  new Point(-1,0));
		wasd.put(83,  new Point(0,1));
		wasd.put(68,  new Point(1,0));
		Game game = new Game(field);
		int size = 100;
		int height = game.getField().getHeigth();
		int width = game.getField().getWidth();

		SnakeHead snakeHead = game.findSnakeHead();
		GUI gui = new GUI(game);
		gui.setPreferredSize(new Dimension(width*size, height*size));

		JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {   
            	Point reversedDirection = new Point(snakeHead.getDirection().x*(-1),
            										snakeHead.getDirection().y*(-1));
            	if (wasd.containsKey(e.getKeyCode()) && 
            			!equalsDirection(wasd.get(e.getKeyCode()), reversedDirection))
            		snakeHead.setDirection(wasd.get(e.getKeyCode()));
                }
          });

        frame.add(gui);
     
        //frame.setPreferredSize(new Dimension(width*size, height*size));    
        frame.pack();

        frame.setVisible(true);
        //frame.setLocationRelativeTo(null);


		frame.setVisible(true);
		//Scanner in = new Scanner(System.in);
		while (!game.gameOver) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//CUI.print(field);
			//String command = in.next();	
			//changeDirection(command, snakeHead);
			game.tick();
			gui.repaint();
			//gui.setVisible(true);
		}
	}
}
