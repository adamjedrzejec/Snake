package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	private int nextPerk = 0;
	private int maxPerk = 200;
	
	private int greenValue = 255;
	
	private int score = 0;
	private int level = 1;
	
	private boolean perkGoDown;
	
	public void tick() {
		nextPerk = (int)Game.clamp(nextPerk, 0, maxPerk);
		greenValue = (int)Game.clamp(greenValue, 0, 255);
		
		greenValue = nextPerk + 55;
		
		if (nextPerk == 0)
			perkGoDown = false;
		
		if (nextPerk == maxPerk)
			perkGoDown = true;
		
		if (perkGoDown) {
			nextPerk--;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH - 260, 55, 200, 32);
		g.setColor(new Color(75, greenValue, 0)); // as in RGB greenValue stands for G
		g.fillRect(Game.WIDTH - 260, 55, nextPerk, 32);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH - 260, 55, 200, 32);
		
		g.fillRect(Game.HEIGHT, 0, 20, Game.HEIGHT);
		
		g.setColor(Color.blue);
		g.drawString("Score: " + score, 25, 64);
		g.drawString("Level: " + level, 25, 80);
	}
	
	
	public void setNextPerk(int nextPerk) {
		this.nextPerk = nextPerk;
	}
	
	public int getNextPerk() {
		return nextPerk;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
}