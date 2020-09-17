package com.ajonx.game.map;

import com.ajonx.game.gfx.Screen;
import com.ajonx.game.gfx.Sprite;

public class MapManager {
	public static final Map PALLET_TOWN = new Map("Pallet Town", new Sprite("pallet_town"));;

	public static Map currentMap = PALLET_TOWN;
	public static double xOffset = 0, yOffset = 0;

	public static void render(Screen screen) {
		currentMap.render(screen, xOffset, yOffset);
	}
}