package gui;
import javax.swing.*;

import fieldObjects.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;

public class GUI extends JPanel implements ActionListener{
	
	public Boolean onTick = false;
	public FieldObject[][] map;
	public int width;
	public int height;
	public int size;
	public Game game;
	private Painter painter;


//public String getSymb(Class clazz) {
//		
//		if (clazz == SnakeHead.class)
//			return "0_0";
//		else if (clazz == EmptyCell.class)
//			return " ";
//		else if (clazz == SnakePart.class)
//			return "F";
//		else if (clazz == Wall.class)
//			return "#";
//		else if (clazz == Apple.class)
//			return "Apple";
//		return "";
//	}

	public void paint(Graphics g) {
		//setBackground(Color.black); 
		this.map = game.getField().getField();
		g.clearRect(0, 0, width*size, height*size);
		
		for (int x = 0; x< this.width;x++) {
			for (int y = 0 ; y< this.height; y ++) {
				//String t = this.getSymb(this.map[j][i].getClass());
				Animation animation = painter.getAnimation(this.map[x][y]);
				if (this.onTick)
					animation.changeImage();
				g.drawImage(animation.getPicture(), x*size, y*size,null);
				//g.drawString(t,j*size,i*size);  
			}
		}
		this.onTick = false;
	}

	public GUI(Game game, int size) {
		this.painter = new Painter();
		this.game = game;
		this.map = game.getField().getField();
		SnakeHead head = game.findSnakeHead();
		int height = game.getField().getHeigth();
		int width = game.getField().getWidth();
		this.width = width;
		this.height = height;
		this.size = size;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
