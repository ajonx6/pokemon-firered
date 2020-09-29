package com.ajonx.game.actors;

import com.ajonx.game.gfx.Sprite;
import com.ajonx.game.gfx.SpriteSheet;
import com.ajonx.game.map.Map;

public class SpriteMovementManager {
	public Player character;
	public Sprite up1, up2, up3;
	public Sprite down1, down2, down3;
	public Sprite left1, left2, left3;
	public Sprite right1, right2, right3;

	public Direction dir;
	public double distance = 0;
	public double timeToTake = 0.32;
	public boolean left = true;

	public SpriteMovementManager(SpriteSheet sheet, Player character) {
		up1 = sheet.getSprite(3);
		up2 = sheet.getSprite(4);
		up3 = sheet.getSprite(5);
		down1 = sheet.getSprite(0);
		down2 = sheet.getSprite(1);
		down3 = sheet.getSprite(2);
		left1 = sheet.getSprite(6);
		left2 = sheet.getSprite(7);
		left3 = sheet.getSprite(8);
		right1 = sheet.getSprite(9);
		right2 = sheet.getSprite(10);
		right3 = sheet.getSprite(11);

		this.character = character;
		character.currentSprite = down2;
	}

	public void move(Direction dir) {
		this.dir = dir;
	}

	public void tick(double delta) {
		if (dir == null) return;
		double dDir = delta * Map.TILE_SIZE / timeToTake;
		distance += dDir;
		if (distance < 8.5) {
			if (left) {
				if (dir == Direction.UP) character.currentSprite = up1;
				else if (dir == Direction.DOWN) character.currentSprite = down1;
				else if (dir == Direction.LEFT) character.currentSprite = left1;
				else if (dir == Direction.RIGHT) character.currentSprite = right1;
			} else {
				if (dir == Direction.UP) character.currentSprite = up3;
				else if (dir == Direction.DOWN) character.currentSprite = down3;
				else if (dir == Direction.LEFT) character.currentSprite = left3;
				else if (dir == Direction.RIGHT) character.currentSprite = right3;
			}
		} else {
			if (dir == Direction.UP) character.currentSprite = up2;
			else if (dir == Direction.DOWN) character.currentSprite = down2;
			else if (dir == Direction.LEFT) character.currentSprite = left2;
			else if (dir == Direction.RIGHT) character.currentSprite = right2;
			if (distance >= 16) {
				left = !left;
				distance = 0;
				dir = null;
			}
		}
	}
}