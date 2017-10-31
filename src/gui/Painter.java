package gui;


import java.io.File;
import java.util.HashMap;

import fieldObjects.*;



public class Painter {
	private HashMap<FieldObject,Animation> objectsRepresentation;
	
	public Painter() {
		this.objectsRepresentation = new HashMap<FieldObject,Animation>();
	}


	public Animation getAnimation(FieldObject obj) {
		if (this.objectsRepresentation.containsKey(obj)){
			return this.objectsRepresentation.get(obj);
		}
		
		String name = obj.getClass().getSimpleName();
		
		Animation animation = new Animation(new File("animations\\"+name),1);
		this.objectsRepresentation.put(obj, animation);
		return animation;
	}
	

}
