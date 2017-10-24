package gui;
import javax.swing.*;

import fieldObjects.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;

public class GUI extends JPanel implements ActionListener{
	
	public FieldObject[][] map;
	public int width;
	public int height;
	public int size;
	public Game game;


public String getSymb(Class clazz) {
		
		if (clazz == SnakeHead.class)
			return "0_0";
		else if (clazz == EmptyCell.class)
			return " ";
		else if (clazz == SnakePart.class)
			return "F";
		else if (clazz == Wall.class)
			return "#";
		else if (clazz == Apple.class)
			return "Apple";
		return "";
	}

	public void paint(Graphics g) {
		//setBackground(Color.black); 
		this.map = game.getField().getField();
		g.clearRect(0, 0, width*size, height*size);
		
		for (int i = 0; i< this.width;i++) {
			for (int j = 0 ; j< this.height; j ++) {
				String t = this.getSymb(this.map[j][i].getClass());
				g.drawString(t,j*size,i*size);  
			}
		}
	}

	public GUI(Game game) {
		this.game = game;
		this.map = game.getField().getField();
		SnakeHead head = game.findSnakeHead();
		int height = game.getField().getHeigth();
		int width = game.getField().getWidth();
		this.width = width;
		this.height = height;
		this.size = 100;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
