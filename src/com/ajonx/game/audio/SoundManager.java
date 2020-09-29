package com.ajonx.game.audio;

import java.util.HashMap;

// Plays and manages the different sounds
public class SoundManager implements Runnable {
	public static HashMap<String, Sound> backgroundMusic = new HashMap<>();
	public static HashMap<String, Sound> soundEffects = new HashMap<>();

	public static Sound currentBGMusic;
	public static Thread thread;
	public static boolean running = false;

	public void start() {
		thread = new Thread(this, "SoundManager");
		thread.start();
	}

	public void run() {
		backgroundMusic.put("pallet_town", new Sound("pallet_town").loop());
		backgroundMusic.put("wild", new Sound("wild").loop());

		soundEffects.put("click", new Sound("click"));

		// SoundManager.changeBackgroundMusic("pallet_town");
	}

	public static void changeBackgroundMusic(String name) {
		if (!backgroundMusic.containsKey(name)) {
			System.err.println("Cannot find background music \"" + name + "\"");
			return;
		}
		if (currentBGMusic != null) currentBGMusic.stop();
		currentBGMusic = backgroundMusic.get(name);
		currentBGMusic.play();
	}

	public static void playSoundEffect(String name) {
		if (!soundEffects.containsKey(name)) {
			System.err.println("Cannot find sound effect \"" + name + "\"");
			return;
		}
		soundEffects.get(name).play();
	}
}