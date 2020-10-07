package com.ajonx.game.map;

import com.ajonx.game.gfx.Screen;
import com.ajonx.game.gfx.Sprite;

public class Map {
	public static final int TILE_SIZE = 16;

	private String name;
	private Sprite layer1;
	private int tileWidth, tileHeight;

	public Map(String name, Sprite layer1) {
		this.name = name;
		this.layer1 = layer1;
		this.tileWidth = layer1.getWidth() / TILE_SIZE;
		this.tileHeight = layer1.getHeight() / TILE_SIZE;
	}

	public void render(Screen screen, double xo, double yo) {
		screen.render(layer1, xo, yo);
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return tileWidth;
	}

	public int getHeight() {
		return tileHeight;
	}
}