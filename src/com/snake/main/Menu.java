package com.snake.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.snake.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			
			//play button
			if (mouseOver(mx, my, 380, 250, 300, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.START_XY, Game.START_XY, ID.Player, handler, hud, game));
				handler.addObject(new Food(Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, ID.Food));
			
				hud.setScore(0);
				hud.setSnakeLength(1);
			}
			
			//high scores button
			if(mouseOver(mx, my, 380, 350, 300, 64)) {
				game.gameState = STATE.HighScores;
			}
			
			//quit button
			if (mouseOver(mx, my, 380, 450, 300, 64)) {
				System.exit(1);
			}
			
		}else if(game.gameState == STATE.HighScores) {
			
			//high scores back button
			if(mouseOver(mx, my, 800, 550, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
			
		}else if(game.gameState == STATE.End) {
			
			//game over back to menu button
			if(mouseOver(mx, my, 275, 500, 230, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
			
			//game over try again button
			if(mouseOver(mx, my, 575, 500, 230, 64)) {
				game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.START_XY, Game.START_XY, ID.Player, handler, hud, game));
				handler.addObject(new Food(Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, ID.Food));
				
				hud.setScore(0);
				hud.setSnakeLength(1);
				
				return;
			}
			
		}

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x + width) {
			if(mouseY > y && mouseY < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setColor(Color.black);
			
			g.setFont(fnt);
			
			g.drawString("Menu", 470, 150);
			
			g.setFont(fnt2);
			
			g.drawRect(380, 250, 300, 64);
			g.drawString("Play", 500, 290);
			
			g.drawRect(380, 350, 300, 64);
			g.drawString("High Scores", 450, 390);
			
			g.drawRect(380, 450, 300, 64);
			g.drawString("Quit", 500, 490);
			
		}else if(game.gameState == STATE.HighScores) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setColor(Color.black);
			g.setFont(fnt);
			
			g.drawString("High Scores", 420, 150);
			
			g.setFont(fnt2);
			
			g.drawRect(800, 550, 200, 64);
			g.drawString("Back", 860, 592);
			
		}else if(game.gameState == STATE.End) {
			
			Font fnt = new Font("arial", 1, 80);
			Font fontNewHighScore = new Font("arial", 1, 45);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setColor(Color.black);
			g.setFont(fnt);
			
			g.drawString("Game over", 340, 150);
			
			g.setFont(fnt2);
			
			g.drawString("You lost with a score of: " + hud.getScore(), 350, 340);
			
			g.drawString("High score: " + hud.getScore(), 450, 420);
			
			g.drawRect(275, 500, 230, 64);
			g.drawString("Back to menu", 293, 542);
			
			g.drawRect(575, 500, 230, 64);
			g.drawString("Try again", 625, 542);
			
			g.setColor(new Color(240, 20, 20));
			g.setFont(fontNewHighScore);
			g.drawString("!!! NEW HIGH SCORE !!!", 300, 270);
		}
		

	}
}
