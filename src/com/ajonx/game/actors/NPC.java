package com.ajonx.game.actors;

import java.awt.event.KeyEvent;

import com.ajonx.game.KeyInput;
import com.ajonx.game.gfx.Screen;
import com.ajonx.game.gfx.Sprite;
import com.ajonx.game.gfx.SpriteSheet;
import com.ajonx.game.map.MapManager;

public class NPC {
	public int tileX, tileY;
	public double worldX, worldY;
	public double screenX, screenY;

	public SpriteSheet sheet;
	public Sprite currentSprite;

	public MovementManager moveManager;
	public NPCSpriteManager spriteManager;

	public NPC(int x, int y) {
		sheet = new SpriteSheet("prof_oak", 16, 24);
		spriteManager = new NPCSpriteManager(sheet, this);

		this.tileX = x;
		this.tileY = y;
		this.worldX = tileX * 16 + 8 - currentSprite.getWidth() / 2;
		this.worldY = tileY * 16 + 5 - currentSprite.getHeight() / 2;
		this.screenX = worldX + MapManager.xOffset;
		this.screenY = worldY + MapManager.yOffset;

		moveManager = new MovementManager(this);
	}

	public void move(double delta) {
		if (!moveManager.moving) {
			if (KeyInput.isDown(KeyEvent.VK_UP)) {
				moveManager.startMove(Direction.UP);
				spriteManager.move(Direction.UP);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_DOWN)) {
				moveManager.startMove(Direction.DOWN);
				spriteManager.move(Direction.DOWN);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_LEFT)) {
				moveManager.startMove(Direction.LEFT);
				spriteManager.move(Direction.LEFT);
			}
			if (!moveManager.moving && KeyInput.isDown(KeyEvent.VK_RIGHT)) {
				moveManager.startMove(Direction.RIGHT);
				spriteManager.move(Direction.RIGHT);
			}
		}
	}

	public void tick(double delta) {
		move(delta);
		if (moveManager.moving) moveManager.updateCoords(delta);
		spriteManager.tick(delta);
		if (!(this instanceof Player)) {
			screenX = worldX + MapManager.xOffset;
			screenY = worldY + MapManager.yOffset;
		}
	}

	public void render(Screen screen) {
		screen.render(currentSprite, screenX, screenY);
	}

	public int getSpriteWidth() {
		return sheet.getSpriteWidth();
	}

	public int getSpriteHeight() {
		return sheet.getSpriteHeight();
	}
}