package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Board extends GameObject {

	public Board(float x, float y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(103, 205, 77));
		g.fillRect((int) (0.1 * Game.HEIGHT), (int) (0.1 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (0.1 * Game.HEIGHT), (int) (0.1 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT));
	}

}
