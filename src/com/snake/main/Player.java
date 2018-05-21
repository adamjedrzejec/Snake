package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	HUD hud;
	
	public Player(float x, float y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		
		
		x = Game.clamp(x, 0, Game.HEIGHT - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 68);

		//handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 32, 32, 0.08f, handler));
		
		collision();
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Food) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hud.setNextPerk(hud.getNextPerk() + 20);
					hud.setScore(hud.getScore() + 1);
					handler.removeObject(tempObject);
					handler.addObject(new Food(r.nextInt(Game.HEIGHT - 32), r.nextInt(Game.HEIGHT - 50), ID.Food));
				}
			}
		}
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	

}
