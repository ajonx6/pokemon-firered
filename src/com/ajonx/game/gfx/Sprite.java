package com.ajonx.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private int width, height;
	private int[] pixels;

	public Sprite(String name) {
		try {
			BufferedImage img = ImageIO.read(new File("res/" + name + ".png"));
			width = img.getWidth();
			height = img.getHeight();
			pixels = new int[width * height];
			pixels = img.getRGB(0, 0, width, height, null, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite(int[] pixels, int sw, int sh) {
		this.width = sw;
		this.height = sh;
		this.pixels = new int[sw * sh];
		System.arraycopy(pixels, 0, this.pixels, 0, pixels.length);
	}

	public int getPixel(int x, int y) {
		return pixels[x + y * width];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}
}