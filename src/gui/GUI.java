package gui;
import javax.swing.*;

import fieldObjects.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import game.Game;
import utils.Point;

public class GUI extends JPanel{
	
	public Boolean onTick = false;
	public FieldObject[][] field;
	public int width;
	public int height;
	public int size;
	public Game game;
	private SnakeCustomizer snakeCustomizer;
	private Painter painter;

	public void paint(Graphics g) {
		this.field = game.getField().getField();
		g.clearRect(0, 0, width*size, height*size);
		
		for (int x = 0; x < this.width; x++) {
			for (int y = 0 ; y < this.height; y++) {
				Animation animation = painter.getAnimation(this.field[x][y]);
				if (this.onTick)
					animation.changeImage();
				g.drawImage(animation.getPicture(), x*size, y*size, null);
			}
		}
		this.onTick = false;
	}

	public GUI(Game game, int size, SnakeHead snakeHead) {
		Map<Integer,Point> wasd = new HashMap<Integer,Point>();
		wasd.put(87,  new Point(0,-1));
		wasd.put(65,  new Point(-1,0));	
		wasd.put(83,  new Point(0,1));
		wasd.put(68,  new Point(1,0));
		height = game.getField().getHeigth();
		width = game.getField().getWidth();
		this.painter = new Painter();
		this.game = game;
		this.field = game.getField().getField();
		this.size = size;
		snakeCustomizer = new SnakeCustomizer(game, size, painter);
		
		this.setPreferredSize(new Dimension(width*size, height*size));

		JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            	if (wasd.containsKey(e.getKeyCode()) && 
            			game.canChangeDirection(snakeHead, wasd.get(e.getKeyCode()))) {
            		snakeHead.setDirection(wasd.get(e.getKeyCode()));
            		}
            	}
          });
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
		while (!game.gameOver) {
			try {
				TimeUnit.MILLISECONDS.sleep(game.getSpeed());
			}
			catch (InterruptedException e) {}
			game.tick();
			this.onTick = true;
			this.repaint();
		}
	}
}
