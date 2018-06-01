package com.snake.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn (Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if (hud.getNextPerk() >= 100) {
			scoreKeep = 0;
			hud.setSnakeLength(hud.getSnakeLength() + 1);
			
			if (hud.getSnakeLength() == 2) {
				handler.addObject(new Food(r.nextInt(Game.HEIGHT - 32), r.nextInt(Game.HEIGHT - 50), ID.Food));
			}
		}
	}
}