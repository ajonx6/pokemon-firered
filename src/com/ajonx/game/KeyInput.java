package com.ajonx.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private static final int NUM_KEYS = 256;
	private static final boolean[] keys = new boolean[NUM_KEYS];
	private static final boolean[] keysLastTick = new boolean[NUM_KEYS];

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public static void tick() {
		for (int i = 0; i < NUM_KEYS; i++) {
			keysLastTick[i] = keys[i];
		}
	}

	public static boolean isDown(int key) {
		return keys[key];
	}

	public static boolean wasPressed(int key) {
		return isDown(key) && !keysLastTick[key];
	}

	public static boolean wasReleased(int key) {
		return !isDown(key) && keysLastTick[key];
	}
}