package com.snake.main;

import java.awt.Graphics;
import java.util.LinkedList;

// Updates and renders all objects in our game
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render (Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject (GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject (GameObject object) {
		this.object.remove(object);
	}
}
