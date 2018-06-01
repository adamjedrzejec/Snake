package com.snake.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.snake.main.Game.STATE;

public class HUD extends MouseAdapter {

	private int nextPerk = 0;
	private int maxPerk = 200;
	
	private int greenValue = 255;
	
	private int score = 0;
	private int snakeLength = 1;
	
	private boolean perkGoDown;
	
	private Game game;
	private Handler handler;
	
	public HUD(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void tick() {
		nextPerk = (int)Game.clamp(nextPerk, 0, maxPerk);
		greenValue = (int)Game.clamp(greenValue, 0, 255);
		
		greenValue = nextPerk + 55;
		
		if (nextPerk == 0)
			perkGoDown = false;
		
		if (nextPerk == maxPerk)
			perkGoDown = true;
		
		if (perkGoDown) {
			nextPerk -= 2;
		}
	}
	
	public void render(Graphics g) {
		/*g.setColor(Color.gray);
		g.fillRect(Game.WIDTH - 260, 55, 200, 32);
		g.setColor(new Color(75, greenValue, 0)); // as in RGB greenValue stands for G
		g.fillRect(Game.WIDTH - 260, 55, nextPerk, 32);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH - 260, 55, 200, 32);*/
		
		// right of the screen game details
		g.setFont(new Font("arial", 1, 20));
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH - 290, 100, 210, 50);
		g.setColor(Color.blue);
		g.drawString("High score: " + "50", 850, 130);
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH - 290, 200, 210, 50);
		g.setColor(Color.blue);
		g.drawString("Score: " + score, 870, 230);
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH - 290, 550, 210, 50);
		g.setColor(Color.blue);
		g.drawString("MENU", 880, 580);
		
		
		
		g.setColor(Color.blue);
		g.fillRect(Game.HEIGHT, 0, 20, Game.HEIGHT);
		
		g.setColor(Color.blue);

	}
	
	public void mousePressed(MouseEvent e) {
		if(game.gameState == STATE.Game) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			if(mouseOver(mouseX, mouseY, Game.WIDTH - 290, 550, 210, 50)) {
				game.gameState = STATE.Menu;
				
				handler.object.clear();
				handler.tail.clear();
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x + width) {
			if(mouseY > y && mouseY < y + height) {
				return true;
			}else return false;
		}else return false;
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
	
	public void setSnakeLength(int snakeLength) {
		this.snakeLength = snakeLength;
	}
	
	public int getSnakeLength() {
		return snakeLength;
	}
	
}