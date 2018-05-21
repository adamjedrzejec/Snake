package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	HUD hud;
	static int moveCounter = 0;
	
	public Player(float x, float y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		dirX = 1;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)Game.BLOCK, (int)Game.BLOCK);
	}

	public void tick() {
		moveCounter++;
		
		if(moveCounter == 8) {
			x += dirX * Game.BLOCK;
			y += dirY * Game.BLOCK;
			setMoveDoneX(true);
			setMoveDoneY(true);
			moveCounter %= 8;
		}

		
		
		x = Game.clamp(x, Game.MIN_XY, Game.MAX_XY - Game.BLOCK);
		y = Game.clamp(y, Game.MIN_XY, Game.MAX_XY - Game.BLOCK);

		
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
					handler.addObject(new Food(Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, ID.Food));
				}
			}
		}
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, (int)Game.BLOCK, (int)Game.BLOCK);
	}
	

}
