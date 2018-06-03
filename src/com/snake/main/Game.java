package com.snake.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2878146351453558385L;

	public static final int WIDTH = 1100, HEIGHT = 720, ROWS = 22;
	public static final float BLOCK = (float) (0.8 * HEIGHT / ROWS), FOODBLOCK = BLOCK/2, START_XY = (float) (0.1 * HEIGHT + 2 * BLOCK), MIN_XY = (float) (0.1 * HEIGHT) , MAX_XY = (float) (0.1 * HEIGHT + ROWS * BLOCK);
	
	public double amountOfTicks;
	
	public int easyHighScore, mediumHighScore, hardHighScore;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public Color backgroundColor = new Color(255,220,60);
	public Color boardColor = new Color(103, 205, 77);
	
	public enum DIFFICULTY {
		Easy,
		Medium,
		Hard
	}
	
	public DIFFICULTY gameDifficulty = DIFFICULTY.Easy;
	
	public enum STATE {
		Menu,
		HighScores,
		Game,
		ChooseLevel,
		End
	}
	
	public STATE gameState = STATE.Menu;
	
	public Game(){
		
		File file = new File("Numbers.txt");
		
		if(!file.exists() && !file.isDirectory()) {
			try {
				PrintWriter output = new PrintWriter("Numbers.txt");
				output.println(0);
				output.println(0);
				output.println(0);
				output.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
	        Scanner input = new Scanner(file);
	        easyHighScore = input.nextInt();
	    	mediumHighScore = input.nextInt();
	    	hardHighScore = input.nextInt();
	        input.close();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		handler = new Handler();
		hud = new HUD(this, handler);
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(hud);
		
		new Window(WIDTH, HEIGHT, "Snake", this);
		
		this.requestFocus();
		
		
		spawner = new Spawn(handler, hud);
		r = new Random();
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(START_XY, START_XY, ID.Player, handler, hud, this));
			handler.addObject(new Food(Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, Game.MIN_XY + Game.BLOCK * r.nextInt(Game.ROWS) + (Game.BLOCK - Game.FOODBLOCK) / 2, ID.Food));
		}
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
		
		long lastTime = System.nanoTime();
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			if(gameDifficulty == DIFFICULTY.Easy)
				amountOfTicks = 8.0;
			else if(gameDifficulty == DIFFICULTY.Medium)
				amountOfTicks = 12.0;
			else if(gameDifficulty == DIFFICULTY.Hard)
				amountOfTicks = 16.0;
			double ns = 1000000000 / amountOfTicks;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
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

		if(gameState == STATE.Game) {
			handler.tick();
			hud.tick();
			//spawner.tick();
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.ChooseLevel) {
			menu.tick();
		}

	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
				
		if(gameState == STATE.Game) {
			g.setColor(boardColor);
			g.fillRect((int) (0.1 * Game.HEIGHT), (int) (0.1 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT), (int) (0.8 * Game.HEIGHT));
			
			handler.render(g);

			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.HighScores || gameState == STATE.End || gameState == STATE.ChooseLevel) {
			menu.render(g);
		}


		
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
