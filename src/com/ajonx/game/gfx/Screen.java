package com.ajonx.game.gfx;

public class Screen {
	private int width, height;
	private int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}

	public void setPixel(int col, int x, int y) {
		double alpha = ((col >> 24) & 0xff) / 255.0;
		double r = (col >> 16) & 0xff, g = (col >> 8) & 0xff, b = col & 0xff;
		double or = (pixels[x + y * width] >> 16) & 0xff, og = (pixels[x + y * width] >> 8) & 0xff, ob = pixels[x + y * width] & 0xff;
		int newr = (int) (or * (1.0 - alpha) + r * alpha);
		int newg = (int) (og * (1.0 - alpha) + g * alpha);
		int newb = (int) (ob * (1.0 - alpha) + b * alpha);
		pixels[x + y * width] = (newr << 16) | (newg << 8) | newb;
	}

	public void render(Sprite sprite, int x, int y) {
		for (int yy = 0; yy < sprite.getHeight(); yy++) {
			int yp = y + yy;
			for (int xx = 0; xx < sprite.getWidth(); xx++) {
				int xp = x + xx;
				if (xp < 0 || yp < 0 || xp >= width || yp >= height) continue;
				setPixel(sprite.getPixel(xx, yy), xp, yp);
			}
		}
	}

	public void clear() {
		clear(0);
	}

	public void clear(int col) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = col;
		}
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