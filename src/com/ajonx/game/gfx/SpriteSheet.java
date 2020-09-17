package com.ajonx.game.gfx;

public class SpriteSheet {
	private int spriteWidth, spriteHeight;
	private Sprite sprite;
	private Sprite[] sprites;

	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		this.sprite = new Sprite(path);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		generateSprites();
	}

	private void generateSprites() {
		int numSpriteX = sprite.getWidth() / spriteWidth;
		int numSpriteY = sprite.getHeight() / spriteHeight;

		this.sprites = new Sprite[numSpriteX * numSpriteY];

		int spriteIndex = 0;
		for (int sy = 0; sy < numSpriteY; sy++) {
			for (int sx = 0; sx < numSpriteX; sx++) {
				int[] currentPixels = new int[spriteWidth * spriteHeight];
				for (int py = 0; py < spriteHeight; py++) {
					int yy = sy * spriteHeight + py;
					for (int px = 0; px < spriteWidth; px++) {
						int xx = sx * spriteWidth + px;
						currentPixels[px + py * spriteWidth] = sprite.getPixel(xx, yy);
					}
				}
				Sprite spr = new Sprite(currentPixels, spriteWidth, spriteHeight);
				sprites[spriteIndex++] = spr;
			}
		}
	}

	public Sprite getSprite(int i) {
		return sprites[i];
	}

	public Sprite getSprite(int x, int y) {
		return sprites[x + y * sprite.getWidth() / spriteWidth];
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}
}