package com.snake.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] moveDirection = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		moveDirection[0] = false;
		moveDirection[1] = false;
		moveDirection[2] = false;
		moveDirection[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_UP) { if(tempObject.getDirY() == 0 && tempObject.getMoveDoneX() == true) { tempObject.setDirY(-1); tempObject.setDirX(0); tempObject.setMoveDoneY(false); } }
				if (key == KeyEvent.VK_DOWN) { if(tempObject.getDirY() == 0 && tempObject.getMoveDoneX() == true) { tempObject.setDirY(1); tempObject.setDirX(0); tempObject.setMoveDoneY(false); } }
				if (key == KeyEvent.VK_RIGHT) { if(tempObject.getDirX() == 0 && tempObject.getMoveDoneY() == true) { tempObject.setDirY(0); tempObject.setDirX(1); tempObject.setMoveDoneX(false); } }
				if (key == KeyEvent.VK_LEFT) { if(tempObject.getDirX() == 0 && tempObject.getMoveDoneY() == true) { tempObject.setDirY(0); tempObject.setDirX(-1); tempObject.setMoveDoneX(false); } }
			}
		}
		
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	/*public void keyReleased(KeyEvent e) {

			
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_UP) { keyDown[0] = false; if(keyDown[1]) tempObject.setVelY(5); else tempObject.setVelY(0); }
				if (key == KeyEvent.VK_DOWN) { keyDown[1] = false; if(keyDown[0]) tempObject.setVelY(-5); else tempObject.setVelY(0); }
				if (key == KeyEvent.VK_RIGHT) { keyDown[2] = false; if(keyDown[3]) tempObject.setVelX(-5); else tempObject.setVelX(0); }
				if (key == KeyEvent.VK_LEFT) { keyDown[3] = false; if(keyDown[2]) tempObject.setVelX(5); else tempObject.setVelX(0); }
			}
		}
	}*/
}
