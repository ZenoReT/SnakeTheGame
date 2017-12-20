package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class Animation {
	private static HashMap<File, HashMap<String, ArrayList<Image>>> uploadedImages = 
			new HashMap<>();
	 private HashMap<String, ArrayList<Image>> images;
	 private int ip;
	 private int frequency;
	 private int tickCount;
	 private String currentAnimation = "1";
	 
	 private static Image errorImage = null;
	 
	 
	 public Animation(File folder, int frequency) {
		 if (errorImage == null) {
			 try {
				 errorImage = ImageIO.read(new File("animations\\IfNotFound\\1.png"));
			 }
			 catch (IOException e) {};
		 }
		 
		 images = new HashMap<>();
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
			 this.images = new HashMap<>();
			 File[] pics = folder.listFiles();
			 if (pics == null || pics.length == 0) {
				 images.put("Error", new ArrayList<Image>());
				 images.get("Error").add(errorImage);
				 return;
			 }
			 for (File pic : pics) {
			 		try {
			 			String[] parsedPath = pic.getCanonicalPath().split("\\\\");
			 			String fileName = parsedPath[parsedPath.length - 1];
			 			String[] parsed = fileName.split("\\.");
						Image img = ImageIO.read(pic);
						//this.images.add(img);
						if (!images.containsKey(parsed[0])) {
							images.put(parsed[0], new ArrayList<Image>());
						}
						images.get(parsed[0]).add(img);
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
			 ip = (ip+1)%images.get(currentAnimation).size();
			 tickCount = 1;
		 }
	 }
	 
	 public Image getPicture() {
		 if (images.size() == 0)
		 {
			 return errorImage;
		 }
		 return images.get(currentAnimation).get(ip);
	 }
	 
	 public HashMap<String, ArrayList<Image>> getImagesFromFolder(File folder){
		 return uploadedImages.get(folder);
	 }
	 
	 public void changeCurrentAnimation(String skinName) {
		if (images.containsKey(skinName)) {
			currentAnimation = skinName;
		}
	 }
}
