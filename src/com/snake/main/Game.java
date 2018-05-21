package com.snake.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2878146351453558385L;

	public static final int WIDTH = 1100, HEIGHT = 720, ROWS = 15;
	public static final float BLOCK = (float) (0.8 * HEIGHT / ROWS), FOODBLOCK = BLOCK/2, START_XY = (float) (0.1 * HEIGHT + 2 * BLOCK), MIN_XY = (float) (0.1 * HEIGHT) , MAX_XY = (float) (0.1 * HEIGHT + ROWS * BLOCK);
	
	public static final int FPS = 120;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Snake", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		r = new Random();
		
		handler.addObject(new Player(START_XY, START_XY, ID.Player, handler, hud));
		for(int i = 0; i < 1; i++)
			handler.addObject(new Food(Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, ID.Food));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			/*try {
				Thread.sleep(800/FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		//spawner.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		g.setColor(new Color(103, 205, 77));
		g.fillRect((int) (0.1 * Game.HEIGHT), (int) (0.1 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT));
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}

}
