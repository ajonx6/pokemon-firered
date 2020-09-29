package com.ajonx.game.actors;

import com.ajonx.game.gfx.Sprite;
import com.ajonx.game.gfx.SpriteSheet;
import com.ajonx.game.map.Map;

public class NPCSpriteManager {
	public Sprite up1, up2, up3;
	public Sprite down1, down2, down3;
	public Sprite left1, left2, left3;
	public Sprite right1, right2, right3;

	public NPC npc;
	public Direction dir;
	public boolean left = true;
	public double totalDistance = 0;
	public double timeToTake = 0.36;

	public NPCSpriteManager(SpriteSheet sheet, NPC npc) {
		this.up1 = sheet.getSprite(3);
		this.up2 = sheet.getSprite(4);
		this.up3 = sheet.getSprite(5);
		this.down1 = sheet.getSprite(0);
		this.down2 = sheet.getSprite(1);
		this.down3 = sheet.getSprite(2);
		this.left1 = sheet.getSprite(6);
		this.left2 = sheet.getSprite(7);
		this.left3 = sheet.getSprite(8);
		this.right1 = sheet.getSprite(9);
		this.right2 = sheet.getSprite(10);
		this.right3 = sheet.getSprite(11);

		this.npc = npc;
		this.npc.currentSprite = sheet.getSprite(1);
	}

	public void move(Direction dir) {
		this.dir = dir;
	}

	public void tick(double delta) {
		if (dir == null) return;
		double dDir = delta * Map.TILE_SIZE / timeToTake;
		totalDistance += dDir;
		if (totalDistance < 8) {
			if (left) {
				if (dir == Direction.UP) npc.currentSprite = up1;
				if (dir == Direction.DOWN) npc.currentSprite = down1;
				if (dir == Direction.LEFT) npc.currentSprite = left1;
				if (dir == Direction.RIGHT) npc.currentSprite = right1;
			} else {
				if (dir == Direction.UP) npc.currentSprite = up3;
				if (dir == Direction.DOWN) npc.currentSprite = down3;
				if (dir == Direction.LEFT) npc.currentSprite = left3;
				if (dir == Direction.RIGHT) npc.currentSprite = right3;
			}
		} else {
			if (dir == Direction.UP) npc.currentSprite = up2;
			if (dir == Direction.DOWN) npc.currentSprite = down2;
			if (dir == Direction.LEFT) npc.currentSprite = left2;
			if (dir == Direction.RIGHT) npc.currentSprite = right2;
			if (totalDistance >= 16) {
				dir = null;
				totalDistance = 0;
				left = !left;
			}
		}
	}
}