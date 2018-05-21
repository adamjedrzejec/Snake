package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends GameObject {

	private Handler handler;
	
	public Food(float x, float y, ID id) {
		super(x, y, id);

		//this.handler = handler;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)Game.FOODBLOCK, (int)Game.FOODBLOCK);
	}

	public void tick() {
	
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)Game.FOODBLOCK, (int)Game.FOODBLOCK);
	}

}
