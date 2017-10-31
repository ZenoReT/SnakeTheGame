package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class Animation {
	private static HashMap<File,ArrayList<Image>> uploadedImages = 
			new HashMap<File,ArrayList<Image>>();
	 private ArrayList<Image> images;
	 private int ip;
	 private int frequency;
	 private int tickCount;
	 
	 private static Image errorImage = null;
	 
	 
	 public Animation(File folder, int frequency) {
		 if (errorImage == null) {
			 try {
				 errorImage = ImageIO.read(new File("Animations\\ifNotFound\\1.png"));
			 }
			 catch (IOException e) {};
		 }
		 
		 images = new ArrayList<Image>();
		 ip = 0;
		 tickCount = 0;
		 if (frequency <= 0) {
			 this.frequency = 1;
		 }
		 else {
			 this.frequency = frequency;			 
		 }
		 if (Animation.uploadedImages.containsKey(folder))
			 this.images = Animation.uploadedImages.get(folder);
		 else {
			 this.images = new ArrayList<Image>();
			 File[] pics = folder.listFiles();
			 if (pics.length == 0) {
				 images.add(errorImage);
				 return;
			 }
			 for (File pic : pics) {
			 		try {
						Image img = ImageIO.read(pic);
						this.images.add(img);
					} catch (IOException e) {
						e.printStackTrace();
					}
			 	}
			 if (images.size() == 0) {
				 System.out.println(String.format("There are no files in %s", folder.toString()));
			 }
			 Animation.uploadedImages.put(folder, this.images);
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
