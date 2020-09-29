package com.ajonx.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.ajonx.game.actors.NPC;
import com.ajonx.game.actors.Player;
import com.ajonx.game.audio.SoundManager;
import com.ajonx.game.gfx.Screen;
import com.ajonx.game.map.MapManager;

public class Game extends Canvas implements Runnable {
	public static final int SCALE = 2;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final double FPS = 120.0;
	public static final String TITLE = "Pokemon Firered";

	private JFrame window;
	private Thread thread;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public static Player player;

	private Screen screen;
	private KeyInput keyInput;
	private SoundManager soundManager;

	private NPC npc;

	public Game() {
		player = new Player(2, 2);
		screen = new Screen(WIDTH, HEIGHT);
		keyInput = new KeyInput();
		soundManager = new SoundManager();

		npc = new NPC(3, 3);

		addKeyListener(keyInput);
	}

	public void initWindow() {
		window = new JFrame(TITLE);
		window.setUndecorated(true);
		window.pack();
		window.add(this);
		window.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}

	public void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
		soundManager.start();
	}

	public void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Not my code, unable to find source :(
	public void run() {
		running = true;
		requestFocus();

		@SuppressWarnings("unused")
		int frames = 0, ticks = 0;
		long frameCounter = 0;
		double frameTime = 1.0 / FPS;
		long lastTime = Time.getTime();
		double unprocessedTime = 0;

		while (running) {
			boolean render = false;

			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double) Time.SECOND;
			frameCounter += passedTime;

			while (unprocessedTime > frameTime) {
				render = true;
				unprocessedTime -= frameTime;
				Time.setDelta(frameTime);
				tick();
				ticks++;
				if (frameCounter >= Time.SECOND) {
					// window.setTitle(TITLE + " | FPS: " + frames + ", UPS: " + ticks);
					// System.out.println("FPS: " + frames + ", UPS: " + ticks);
					frames = 0;
					ticks = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	public void tick() {
		double delta = Time.getFrameTimeInSeconds();
		if (KeyInput.isDown(KeyEvent.VK_ESCAPE)) System.exit(0);
		if (KeyInput.wasPressed(KeyEvent.VK_SPACE)) SoundManager.changeBackgroundMusic("wild");
		if (KeyInput.wasPressed(KeyEvent.VK_X)) SoundManager.playSoundEffect("click");

		player.tick(delta);
		npc.tick(delta );
		KeyInput.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		screen.clear();

		MapManager.render(screen);
		player.render(screen);
		npc.render(screen);

		System.arraycopy(screen.getPixels(), 0, pixels, 0, pixels.length);
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.initWindow();
		game.start();
	}
}