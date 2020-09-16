package com.ajonx.game.actors;

import com.ajonx.game.map.Map;
import com.ajonx.game.map.MapManager;

public class MovementManager {
	public Player p;
	public double srcX, srcY;
	public double dstX, dstY;
	public Direction direction;

	public double distanceTravelled = 0;
	public double timeToTake = 0.4;
	public boolean moving = false;

	public MovementManager(Player p, double x, double y) {
		this.p = p;
		moveDirectToTile(x, y);
	}

	public void startMove(Direction dir) {
		if (dir == Direction.UP) {
			if (dstY == 0) return;
			dstY = srcY - 1;
		} else if (dir == Direction.DOWN) {
			if (dstY == MapManager.currentMap.getHeight() - 1) return;
			dstY = srcY + 1;
		} else if (dir == Direction.LEFT) {
			if (dstX == 0) return;
			dstX = srcX - 1;
		} else if (dir == Direction.RIGHT) {
			if (dstX == MapManager.currentMap.getWidth() - 1) return;
			dstX = srcX + 1;
		}
		this.direction = dir;
		moving = true;
	}

	// When a key is pressed, will move the length of a tile in the time "timeToTake"
	public void updateCoords(double delta) {
		double dDir = delta * Map.TILE_SIZE / timeToTake;
		if (direction == Direction.UP) {
			p.setY(p.getY() - dDir);
		}
		if (direction == Direction.DOWN) {
			p.setY(p.getY() + dDir);
		}
		if (direction == Direction.LEFT) {
			p.setX(p.getX() - dDir);
		}
		if (direction == Direction.RIGHT) {
			p.setX(p.getX() + dDir);
		}
		distanceTravelled += dDir;

		if (distanceTravelled >= 16) {
			moveDirectToTile(dstX, dstY);
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
		p.setX((x + 0.5) * Map.TILE_SIZE - p.getSpriteWidth() / 2.0);
		p.setY((y + 1.0) * Map.TILE_SIZE - p.getSpriteHeight() + 1.0);
		srcX = dstX = x;
		srcY = dstY = y;
	}
}