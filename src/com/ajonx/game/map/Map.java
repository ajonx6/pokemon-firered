package com.ajonx.game.map;

import com.ajonx.game.gfx.Screen;
import com.ajonx.game.gfx.Sprite;

public class Map {
	public static final int TILE_SIZE = 16;

	private String name;
	private Sprite map;
	private int tileWidth, tileHeight;

	public Map(String name, Sprite map) {
		this.name = name;
		this.map = map;
		this.tileWidth = map.getWidth() / TILE_SIZE;
		this.tileHeight = map.getHeight() / TILE_SIZE;
	}

	public void render(Screen screen, double xo, double yo) {
		screen.render(map, xo, yo);
	}

	public String getName() {
		return name;
	}

	public Sprite getMap() {
		return map;
	}

	public int getWidth() {
		return tileWidth;
	}

	public int getHeight() {
		return tileHeight;
	}
}