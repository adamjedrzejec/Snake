package com.snake.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected int dirX, dirY;
	protected boolean moveDoneX = false, moveDoneY = false; // is move after change done
	
	public GameObject (float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX (float x) {
		this.x = x;
	}
	public void setY (float y) {
		this.y = y;
	}
	public float getX () {
		return x;
	}
	public float getY () {
		return y;
	}
	public void setID (ID id) {
		this.id = id;
	}
	public ID getID () {
		return id;
	}
	public void setDirX (int dirX) {
		this.dirX = dirX;
	}
	public void setDirY (int dirY) {
		this.dirY = dirY;
	}
	public int getDirX () {
		return dirX;
	}
	public int getDirY () {
		return dirY;
	}
	public void setMoveDoneX(boolean moveDoneX) {
		this.moveDoneX = moveDoneX;
	}
	public boolean getMoveDoneX() {
		return moveDoneX;
	}
	public void setMoveDoneY(boolean moveDoneY) {
		this.moveDoneY = moveDoneY;
	}
	public boolean getMoveDoneY() {
		return moveDoneY;
	}
	
}
