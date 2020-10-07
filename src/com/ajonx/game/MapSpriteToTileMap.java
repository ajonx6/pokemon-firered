package com.ajonx.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.ajonx.game.gfx.Sprite;
import com.ajonx.game.gfx.SpriteSheet;

/*
 * This class will take a map sprite, convert it into a tile sheet and then generate the 
 * to recreate the file back in the game
 */
public class MapSpriteToTileMap {
	public static Scanner scanner = new Scanner(System.in);
	public static List<int[]> tiles = new ArrayList<>();
	public static List<Integer> map = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		System.out.println("Enter the map to breakdown:");
		String name = scanner.next();
		BufferedImage img = ImageIO.read(new File("res/" + name + ".png"));
		SpriteSheet sheet = new SpriteSheet(name, 16, 16);
		int w = img.getWidth(), h = img.getHeight();
		int tilex = w / 16;
		int tiley = h / 16;

		int[] blank = new int[16 * 16];
		tiles.add(blank);

		// Loops through each tile and if it has not been seen before, add it to the tile sheet
		// Also adds the index of each tile to the map file in order to recreate it
		loop: for (int i = 0; i < tilex * tiley; i++) {
			int[] pixels = sheet.getSprite(i).getPixels();
			for (int j = 0; j < tiles.size(); j++) {
				int[] p = tiles.get(j);
				if (Arrays.equals(pixels, p)) {
					map.add(j);
					continue loop;
				}
			}
			map.add(tiles.size());
			tiles.add(pixels);
		}

		// Generate the tile sheet from the saved pixel data
		Sprite tilemap = new Sprite(null, tiles.size() * 16, 16);
		for (int i = 0; i < tiles.size(); i++) {
			int[] t = tiles.get(i);
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					tilemap.setPixel(x + i * 16, y, t[x + y * 16]);
				}
			}
		}
		tilemap.save("/maps/" + name + "/tiles");

		System.out.println("What is the name of the map to save:");
		String mapname = scanner.nextLine();
		List<String> data = new ArrayList<>();
		data.add(mapname);
		data.add(tilex + "," + tiley);
		int index = 0;
		for (int i = 0; i < tiley; i++) {
			String line = "";
			for (int j = 0; j < tilex; j++) {
				if (j > 0) line += ",";
				String value = Integer.toString(map.get(index++));
				if (value.length() == 1) value = "0" + value;
				line += value;
			}
			data.add(line);
		}
		Util.writeTextFile("maps/" + name + "/map.map", data);

		System.out.println("Generated SpriteSheet to res/" + name + "_tiles.png");
	}
}