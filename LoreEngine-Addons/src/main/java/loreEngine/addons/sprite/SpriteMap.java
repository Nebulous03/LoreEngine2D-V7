package loreEngine.addons.sprite;

import loreEngine.core.graphics.texture.Texture;

public class SpriteMap {
	
	private Texture mapTexture;
	private int spriteSize;
	
	private Sprite[] sprites;

	public SpriteMap(Texture mapTexture, int spriteSize) {
		this.mapTexture = mapTexture;
	}
	
	public Sprite[] generateSpriteArray(Texture texture, int spriteSize) {
		
		int width  = texture.getWidth();
		int height = texture.getHeight();
		
		sprites = new Sprite[width * height];
		
		for(int y = 0; y < height / spriteSize; y += spriteSize) {
			for(int x = 0; x < width / spriteSize; x += spriteSize) {
				sprites[x + y * width] = new Sprite(spriteSize, x, y);
			}
		}
		
		return null;
	}

	public Texture getMapTexture() {
		return mapTexture;
	}

	public int getSpriteSize() {
		return spriteSize;
	}

	public Sprite[] getSprites() {
		return sprites;
	}
	
}
