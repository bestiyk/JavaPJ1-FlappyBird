/*
 * Copyright (c) 2019.
 * Author Matyas Dedek
 * Project JavaPJ1-FlappyBird
 *
 */

/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package assets;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static Keyboard instance;

	private boolean[] keys;
	
	private Keyboard() {
		keys = new boolean[256];
	}

	public static Keyboard getInstance() {

		if (instance == null) {
			instance = new Keyboard();
		}
		
		return instance;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
			keys[e.getKeyCode()] = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
			keys[e.getKeyCode()] = false;
		}
	}
	//returns true if a specific key is registered
	public boolean isDown(int key) {

		if (key >= 0 && key < keys.length) {
			return keys[key];
		}
		
		return false;
	}
	
	public void keyTyped(KeyEvent e) {}
}
