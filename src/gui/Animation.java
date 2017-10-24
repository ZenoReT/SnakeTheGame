package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class Animation {
	 private ArrayList<Image> Images;
	 private int IP;
	 private int Frequency;
	 private int TickCount;
	 
	 public Animation(File folder, int frequency) {
		 this.Images = new ArrayList<Image>();
		 this.IP = 0;
		 this.TickCount = 0;
		 this.Frequency = frequency;
		 File[] pics = folder.listFiles();
		 //System.out.print(pics.toString());
		 for (File pic : pics) {
		 		try {
					Image img = ImageIO.read(pic);
					this.Images.add(img);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}	 	
	 }
	 
	 public void changeImage() {
		 this.TickCount++;
		 if (this.TickCount%this.Frequency == 0){
			 this.IP = (this.IP+1)%this.Images.size();
			 this.TickCount = 1;
		 }
	 }
	 
	 public Image getPicture() {
		 return this.Images.get(this.IP);
	 }

}
