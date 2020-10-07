package com.ajonx.game.map;

import java.util.List;

import com.ajonx.game.Util;
import com.ajonx.game.gfx.Sprite;
import com.ajonx.game.gfx.SpriteSheet;

public class MapLoader {
	public static Map load(String path) {
		List<String> file = Util.loadTextFile("maps/" + path + "/map.map");
		SpriteSheet sheet = new SpriteSheet("maps/" + path + "/tiles", Map.TILE_SIZE, Map.TILE_SIZE);

		String name = file.get(0);
		int width = Integer.parseInt(file.get(1).split(",")[0]);
		int height = Integer.parseInt(file.get(1).split(",")[1]);

		Sprite layer1 = new Sprite(width * Map.TILE_SIZE, height * Map.TILE_SIZE);
		for (int y = 0; y < height; y++) {
			String line = file.get(y + 3);
			String[] indexs = line.split(",");
			for (int x = 0; x < indexs.length; x++) {
				Sprite s = sheet.getSprite(Integer.parseInt(indexs[x]));
				for (int yy = 0; yy < Map.TILE_SIZE; yy++) {
					for (int xx = 0; xx < Map.TILE_SIZE; xx++) {
						layer1.setPixel(x * Map.TILE_SIZE + xx, y * Map.TILE_SIZE + yy, s.getPixel(xx, yy));
					}
				}
			}
		}

		return new Map(name, layer1);
	}
}