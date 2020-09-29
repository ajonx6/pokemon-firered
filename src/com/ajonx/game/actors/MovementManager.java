package com.ajonx.game.actors;

import com.ajonx.game.map.Map;
import com.ajonx.game.map.MapManager;

public class MovementManager {
	public NPC npc;
	public int srcX, srcY;
	public int dstX, dstY;
	public Direction direction;

	public double distanceTravelled = 0;
	public double timeToTake = 0.36;
	public boolean moving = false;

	public MovementManager(NPC npc) {
		this.npc = npc;
		this.srcX = npc.tileX;
		this.srcY = npc.tileY;
	}

	public void startMove(Direction dir) {
		if (dir == Direction.UP) {
			if (dstY == 0) return;
			dstX = srcX;
			dstY = srcY - 1;
		} else if (dir == Direction.DOWN) {
			if (dstY == MapManager.currentMap.getHeight() - 1) return;
			dstX = srcX;
			dstY = srcY + 1;
		} else if (dir == Direction.LEFT) {
			if (dstX == 0) return;
			dstX = srcX - 1;
			dstY = srcY;
		} else if (dir == Direction.RIGHT) {
			if (dstX == MapManager.currentMap.getWidth() - 1) return;
			dstX = srcX + 1;
			dstY = srcY;
		}
		this.direction = dir;
		moving = true;
	}

	// When a key is pressed, will move the length of a tile in the time "timeToTake"
	public void updateCoords(double delta) {
		double dDir = delta * Map.TILE_SIZE / timeToTake;
		if (direction == Direction.UP) {
			if (npc instanceof Player) MapManager.yOffset += dDir;
			else npc.worldY -= dDir;
		}
		if (direction == Direction.DOWN) {
			if (npc instanceof Player) MapManager.yOffset -= dDir;
			else npc.worldY += dDir;
		}
		if (direction == Direction.LEFT) {
			if (npc instanceof Player) MapManager.xOffset += dDir;
			else npc.worldX -= dDir;
		}
		if (direction == Direction.RIGHT) {
			if (npc instanceof Player) MapManager.xOffset -= dDir;
			else npc.worldX += dDir;
		}
		distanceTravelled += dDir;

		if (distanceTravelled >= 16) {
			finishMove();
		}
	}

	public void finishMove() {
		srcX = dstX;
		srcY = dstY;
		distanceTravelled = 0;
		moveDirectToTile(srcX, srcY);
		moving = false;
	}

	// Moves the sprite straight to the correct part of the tile
	public void moveDirectToTile(double x, double y) {
		npc.worldX = dstX * 16 + 8 - npc.getSpriteWidth() / 2.0;
		npc.worldY = dstY * 16 + 5 - npc.getSpriteHeight() / 2.0;
		if (npc instanceof Player) {
			MapManager.xOffset = npc.screenX - npc.worldX;
			MapManager.yOffset = npc.screenY - npc.worldY;
		}

		npc.tileX = (int) dstX;
		npc.tileY = (int) dstY;
	}
}