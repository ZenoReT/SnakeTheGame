package gui;
import javax.imageio.ImageIO;
import javax.swing.*;

import fieldObjects.FieldObject;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import utils.Point;

public class SnakeCustomizer extends JPanel {
	public Boolean onTick = false;
	private int width;
	private int height;
	private int size;
	private static HashMap<FieldObject, HashMap<String, ArrayList<Image>>> images =
			new HashMap<>();
	private JFrame frame;
	private Game game;
	private Painter painter;

	public SnakeCustomizer(Game game, int size, Painter painter) {
		width = 5;
		height = 10;
		this.size = size;
		this.game = game;
		this.painter = painter;
		ArrayList<FieldObject> objectsToCustomize = getObjectsToCustomize();
		fillImages(objectsToCustomize);
        //Сетка с Label
		frame = new JFrame("Customizer");
		this.setPreferredSize(new Dimension(200, 350));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.repaint();
		frame.pack();
		frame.setVisible(true);
	}
	
	private ArrayList<FieldObject> getObjectsToCustomize(){
		ArrayList<FieldObject> objectsToCustomize = new ArrayList<FieldObject>();
		objectsToCustomize.add(new SnakeHead(0, 0, new Point(0, 0)));
		objectsToCustomize.add(new SnakePart(0, 0));
		return objectsToCustomize;
	}
	
	private void fillImages(ArrayList<FieldObject> objectsToCustomize) {
		for (FieldObject customizeObject: objectsToCustomize) {
			String name = customizeObject.getClass().getSimpleName();
			File folder = new File("animations\\" + name);
			File[] pics = folder.listFiles();
			for (File pic : pics) {
				try {
					if (!images.containsKey(customizeObject)) {
					images.put(customizeObject, new HashMap<>());
					}
					String[] parsedPath = pic.getCanonicalPath().split("\\\\");
					String currentName = parsedPath[parsedPath.length - 1];
					currentName = currentName.split("\\.")[0];
					Image img = ImageIO.read(pic);
					if (!images.containsKey(customizeObject)) {
						images.put(customizeObject, new HashMap<>());
					}
					if (!images.get(customizeObject).containsKey(currentName)) {
						images.get(customizeObject).put(currentName, new ArrayList<>());
					}
					images.get(customizeObject).get(currentName).add(img);
				} 
				catch (IOException e) {}
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0, width*size, height*size);
		int maxX = 3;
		int x = 0;
		int y = 0;
		int butWidth = 50;
		int butHeight = 50;
		for (FieldObject customizeObject: images.keySet()) {
			y++;
			g.drawString(customizeObject.getClass().getSimpleName(), size, y * size - size / 2);
			for (String name: images.get(customizeObject).keySet()) {
				for (Image img: images.get(customizeObject).get(name)) {
					g.drawImage(img, x*size, y*size, null);
					x++;
					}

				JButton currentButton = new JButton(name);
				currentButton.setBounds(maxX*size, y*size, butWidth, butHeight);
				currentButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event) {
						ArrayList<FieldObject> objectsToChangeAnimation = game.getLevel().
								getObjectsOf(customizeObject.getClass());
						for (FieldObject currentObject: objectsToChangeAnimation) {
							painter.getAnimation(currentObject).changeCurrentAnimation(name);
						painter.getCurrentAnimations().put(customizeObject.getClass(), name);
						}
						}
					});
				frame.add(currentButton);
				//currentButton.update(g);
				y++;
				x = 0;
			}
		}
	}
}
