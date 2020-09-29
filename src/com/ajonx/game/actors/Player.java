package com.ajonx.game.actors;

import java.awt.event.KeyEvent;

import com.ajonx.game.Game;
import com.ajonx.game.KeyInput;
import com.ajonx.game.gfx.SpriteSheet;
import com.ajonx.game.map.MapManager;

public class Player extends NPC {
	public Player(int x, int y) {
		super(x, y);
		screenX = Game.WIDTH / 2 - currentSprite.getWidth() / 2;
		screenY = Game.HEIGHT / 2 - currentSprite.getHeight() / 2;
		MapManager.xOffset = screenX - worldX;
		MapManager.yOffset = screenY - worldY;

		sheet = new SpriteSheet("player", 16, 24);
		moveManager = new MovementManager(this);
		spriteManager = new NPCSpriteManager(sheet, this);
	}

	public void move(double delta) {
		if (!moveManager.moving) {
			if (KeyInput.isDown(KeyEvent.VK_W)) {
				moveManager.startMove(Direction.UP);
				spriteManager.move(Direction.UP);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_S)) {
				moveManager.startMove(Direction.DOWN);
				spriteManager.move(Direction.DOWN);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_A)) {
				moveManager.startMove(Direction.LEFT);
				spriteManager.move(Direction.LEFT);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_D)) {
				moveManager.startMove(Direction.RIGHT);
				spriteManager.move(Direction.RIGHT);
			}
		}
	}

	public void tick(double delta) {
		super.tick(delta);
	}
}