package com.snake.main;

import java.awt.Graphics;
import java.util.LinkedList;

// Updates and renders all objects in our game
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<GameObject> tail = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < tail.size(); i++) {
			GameObject tempTail = tail.get(i);
			tempTail.tick();
		}
		for(int i = tail.size() - 1; i >= 0; i--) {
			GameObject tempTail = tail.get(i);
			if(tempTail.getExpireCounter() <= 0) {
				this.tail.remove(tempTail);
			}
		}
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
		for (int i = 0; i < tail.size(); i++) {
			GameObject tempTail = tail.get(i);
			tempTail.render(g);
		}
	}
	
	public void addObject (GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject (GameObject object) {
		this.object.remove(object);
	}
	
	public void addTail (GameObject tail) {
		this.tail.add(tail);
	}
	
	public void removeTail (GameObject tail) {
		this.tail.remove(tail);
	}
}
