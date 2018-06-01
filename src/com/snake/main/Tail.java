package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tail extends GameObject {

	Handler handler;
	HUD hud;
	
	public Tail(float x, float y, ID id, Handler handler, HUD hud, int expireCounter) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.expireCounter = expireCounter;
	}

	@Override
	public void tick() {
		expireCounter--;
	}

	@Override
	public void render(Graphics g) {
		int colorG = 10;
		int colorB = (int) Game.clamp(155 + expireCounter * 15, 155, 200);
		if(colorB == 200) {
			colorG = (int) Game.clamp(10 + (expireCounter - 20) * 15, 10, 255);
		}
		g.setColor(new Color(100, colorG, colorB));
		g.fillRect((int)x, (int)y, (int)Game.BLOCK - 1, (int)Game.BLOCK - 1);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)Game.BLOCK, (int)Game.BLOCK);
	}
	
}
