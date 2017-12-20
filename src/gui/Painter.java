package gui;


import java.io.File;
import java.util.HashMap;

import fieldObjects.*;



public class Painter {
	
	private HashMap<FieldObject, Animation> objectsRepresentation;
	private HashMap<Class, String> currentAnimations;
	
	public Painter() {
		currentAnimations = new HashMap<>();
		this.objectsRepresentation = new HashMap<FieldObject, Animation>();
	}


	public Animation getAnimation(FieldObject obj) {
		if (this.objectsRepresentation.containsKey(obj))
		{
			Animation current =  this.objectsRepresentation.get(obj);
			current.changeCurrentAnimation(currentAnimations.get(obj.getClass()));
			return current;
		}
		
		String name = obj.getClass().getSimpleName();
		
		Animation animation = new Animation(new File("animations\\"+name), 1);
		this.objectsRepresentation.put(obj, animation);
		if (!currentAnimations.containsKey(obj.getClass())) {
			currentAnimations.put(obj.getClass(), "1");
		}
		return animation;
	}
	
	public HashMap<Class, String> getCurrentAnimations() {
		return currentAnimations;
	}
}
