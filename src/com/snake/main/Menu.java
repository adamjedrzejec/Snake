package com.snake.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import com.snake.main.Game.DIFFICULTY;
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
				game.gameState = STATE.ChooseLevel;
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
				
				int highScore = 0;
				
				if(game.gameDifficulty == DIFFICULTY.Easy)
					highScore = game.easyHighScore;
				else if(game.gameDifficulty == DIFFICULTY.Medium)
					highScore = game.mediumHighScore;
				else if(game.gameDifficulty == DIFFICULTY.Hard)
					highScore = game.hardHighScore;
				
				if(highScore < hud.getScore()) {
					try {
						File file = new File("Numbers.txt");
						PrintWriter output = new PrintWriter(file);
						
						
						if(game.gameDifficulty == DIFFICULTY.Easy) {
							game.easyHighScore = highScore;
							output.println(hud.getScore());
							output.println(game.mediumHighScore);
							output.println(game.hardHighScore);
						}else if(game.gameDifficulty == DIFFICULTY.Medium) {
							game.mediumHighScore = highScore;
							output.println(game.easyHighScore);
							output.println(hud.getScore());
							output.println(game.hardHighScore);
						}else if(game.gameDifficulty == DIFFICULTY.Hard) {
							game.hardHighScore = highScore;
							output.println(game.easyHighScore);
							output.println(game.mediumHighScore);
							output.println(hud.getScore());
						}
						
						output.close();
				    
					}catch(Exception g) {
						g.printStackTrace();
					}
				}
				return;
			}
			
			//game over try again button
			if(mouseOver(mx, my, 575, 500, 230, 64)) {
				game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.START_XY, Game.START_XY, ID.Player, handler, hud, game));
				handler.addObject(new Food(Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, ID.Food));
				
				
				int highScore = 0;
				
				if(game.gameDifficulty == DIFFICULTY.Easy)
					highScore = game.easyHighScore;
				else if(game.gameDifficulty == DIFFICULTY.Medium)
					highScore = game.mediumHighScore;
				else if(game.gameDifficulty == DIFFICULTY.Hard)
					highScore = game.hardHighScore;
				
				if(highScore < hud.getScore()) {
					try {
						File file = new File("Numbers.txt");
						PrintWriter output = new PrintWriter(file);
						
						
						if(game.gameDifficulty == DIFFICULTY.Easy) {
							output.println(hud.getScore());
							output.println(game.mediumHighScore);
							output.println(game.hardHighScore);
						}else if(game.gameDifficulty == DIFFICULTY.Medium) {
							output.println(game.easyHighScore);
							output.println(hud.getScore());
							output.println(game.hardHighScore);
						}else if(game.gameDifficulty == DIFFICULTY.Hard) {
							output.println(game.easyHighScore);
							output.println(game.mediumHighScore);
							output.println(hud.getScore());
						}
						
						output.close();
				    
					}catch(Exception g) {
						g.printStackTrace();
					}
				}
				
				
				hud.setScore(0);
				hud.setSnakeLength(1);
				
				return;
			}
			
		}else if(game.gameState == STATE.ChooseLevel) {
			
			//choose level easy button
			if(mouseOver(mx, my, 450, 250, 200, 64)) {
				game.gameDifficulty = DIFFICULTY.Easy;
				game.gameState = STATE.Game;
			}
			
			//choose level medium button
			if(mouseOver(mx, my, 450, 350, 200, 64)) {
				game.gameDifficulty = DIFFICULTY.Medium;
				game.gameState = STATE.Game;
			}
			
			//choose level hard button
			if(mouseOver(mx, my, 450, 450, 200, 64)) {
				game.gameDifficulty = DIFFICULTY.Hard;
				game.gameState = STATE.Game;
			}
			
			//choose level back button
			if(mouseOver(mx, my, 800, 550, 200, 64)) {
				game.gameState = STATE.Game;
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
			int highScore = 0;
			
			if(game.gameDifficulty == DIFFICULTY.Easy)
				highScore = game.easyHighScore;
			else if(game.gameDifficulty == DIFFICULTY.Medium)
				highScore = game.mediumHighScore;
			else if(game.gameDifficulty == DIFFICULTY.Hard)
				highScore = game.hardHighScore;
			
			g.setColor(Color.black);
			g.setFont(fnt);
			
			g.drawString("Game over", 340, 150);
			
			g.setFont(fnt2);
			
			g.drawString("You lost with a score of: " + hud.getScore(), 350, 340);
			
			g.drawString("High score: " + highScore, 450, 420);
			
			g.drawRect(275, 500, 230, 64);
			g.drawString("Back to menu", 293, 542);
			
			g.drawRect(575, 500, 230, 64);
			g.drawString("Try again", 625, 542);
			
			if(highScore < hud.getScore()) {
				g.setColor(new Color(240, 20, 20));
				g.setFont(fontNewHighScore);
				g.drawString("!!! NEW HIGH SCORE !!!", 300, 270);
			}
		}else if(game.gameState == STATE.ChooseLevel) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setColor(Color.black);
			g.setFont(fnt);
			
			g.drawString("Choose level", 390, 150);
			
			g.setFont(fnt2);
			
			g.drawRect(450, 250, 200, 64);
			g.drawString("Easy", 512, 292);
			
			g.drawRect(450, 350, 200, 64);
			g.drawString("Medium", 495, 392);
			
			g.drawRect(450, 450, 200, 64);
			g.drawString("Hard", 512, 492);
			
			
			g.drawRect(800, 550, 200, 64);
			g.drawString("Back", 860, 592);
			
		}
		

	}
}
