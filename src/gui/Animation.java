package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class Animation {
	 private ArrayList<Image> images;
	 private int ip;
	 private int frequency;
	 private int tickCount;
	 
	 private Image errorImage = null;
	 
	 public Animation(File folder, int frequency) {
		 try {
			 errorImage = ImageIO.read(new File("Animations\\ifNotFound\\1.png"));
		 }
		 catch (IOException e) {};
		 images = new ArrayList<Image>();
		 ip = 0;
		 tickCount = 0;
		 if (frequency <= 0) {
			 this.frequency = 1;
		 }
		 else {
			 this.frequency = frequency;			 
		 }
		 File[] pics = folder.listFiles();
		 //System.out.print(pics.toString());
		 for (File pic : pics) {
		 		try {
					Image img = ImageIO.read(pic);
					images.add(img);
				} catch (IOException e) {}
		 	}
		 if (images.size() == 0) {
			 System.out.println(String.format("There are no files in %s", folder.toString()));
		 }
	 }
	 
	 public void changeImage() {
		 if (images.size() == 0) {
			 return;
		 }
		 tickCount++;
		 if (tickCount%frequency == 0){
			 ip = (ip+1)%images.size();
			 tickCount = 1;
		 }
	 }
	 
	 public Image getPicture() {
		 if (images.size() == 0)
		 {
			 return errorImage;
		 }
		 return images.get(this.ip);
	 }

}
