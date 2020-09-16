package com.ajonx.game.actors;

import java.awt.event.KeyEvent;

import com.ajonx.game.KeyInput;
import com.ajonx.game.gfx.Screen;
import com.ajonx.game.gfx.Sprite;
import com.ajonx.game.gfx.SpriteSheet;

public class Player {
	private double x = 0, y = 0;
	private SpriteSheet sheet = new SpriteSheet("player", 16, 24);
	private Sprite currentSprite = sheet.getSprite(1);
	private MovementManager moveManager;

	public Player() {
		moveManager = new MovementManager(this, 2, 2);
	}

	private void move(double delta) {
		if (!moveManager.moving) {
			if (KeyInput.isDown(KeyEvent.VK_W)) {
				currentSprite = sheet.getSprite(4);
				moveManager.startMove(Direction.UP);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_S)) {
				currentSprite = sheet.getSprite(1);
				moveManager.startMove(Direction.DOWN);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_A)) {
				currentSprite = sheet.getSprite(7);
				moveManager.startMove(Direction.LEFT);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_D)) {
				currentSprite = sheet.getSprite(10);
				moveManager.startMove(Direction.RIGHT);
			}
		}
	}

	public void tick(double delta) {
		move(delta);
		if (moveManager.moving) moveManager.updateCoords(delta);
	}

	public void render(Screen screen) {
		screen.render(currentSprite, x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getSpriteWidth() {
		return sheet.getSpriteWidth();
	}

	public int getSpriteHeight() {
		return sheet.getSpriteHeight();
	}
}