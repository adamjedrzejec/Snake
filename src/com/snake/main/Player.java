package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.snake.main.Game.STATE;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	HUD hud;
	private Game game;
	
	public Player(float x, float y, ID id, Handler handler, HUD hud, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		dirX = 1;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)Game.BLOCK, (int)Game.BLOCK);
	}

	public void tick() {
		
		handler.addTail(new Tail(x, y, ID.Tail, handler, hud, hud.getSnakeLength()));

		x += dirX * Game.BLOCK;
		y += dirY * Game.BLOCK;
		
		setMoveDoneX(true);
		setMoveDoneY(true);

		checkBoundaries();
		collision();
	}

	
	private void checkBoundaries() {
		if(x < Game.MIN_XY - 1 || x > Game.MAX_XY - 1 || y < Game.MIN_XY - 1 || y > Game.MAX_XY - 1) {
			game.gameState = STATE.End;
			
			handler.object.clear();
			handler.tail.clear();
		}
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
					hud.setSnakeLength(hud.getSnakeLength() + 1);
				}
			}
		}
		
		for(int i = 0; i < handler.tail.size(); i++) {
			
			GameObject tempTail = handler.tail.get(i);
			
			if(tempTail.getID() == ID.Tail) {
				if(getBounds().intersects(tempTail.getBounds())) {
					game.gameState = STATE.End;
					
					handler.object.clear();
					handler.tail.clear();
				}
			}
		}
		
	}
	
	
	public void render(Graphics g) {
		g.setColor(new Color(180,0,0));
		g.fillRect((int)x, (int)y, (int)Game.BLOCK - 1, (int)Game.BLOCK - 1);
	}
	

}
