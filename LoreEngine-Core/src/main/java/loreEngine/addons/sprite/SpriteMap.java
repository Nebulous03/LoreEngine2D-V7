package loreEngine.addons.sprite;

import loreEngine.addons.tileMap.TileSprite;
import loreEngine.core.graphics.texture.Texture;

public class SpriteMap {
	
	private int width;
	private int height;
	
	private Texture mapTexture;
	private int spriteSize;
	
	private TileSprite[] sprites;

	public SpriteMap(Texture mapTexture, int spriteSize) {
		this.mapTexture = mapTexture;
		this.spriteSize = spriteSize;
	}
	
	public TileSprite[] generateSpriteArray(Texture texture, int spriteSize) {
		
		width  = texture.getWidth();
		height = texture.getHeight();
		
		sprites = new TileSprite[width * height];
		
		for(int y = 0; y < height / spriteSize; y += spriteSize) {
			for(int x = 0; x < width / spriteSize; x += spriteSize) {
				sprites[x + y * width] = new TileSprite(x + y * width, spriteSize, x, y);
			}
		}
		
		return null;
	}

	public Texture getTexture() {
		return mapTexture;
	}

	public int getSpriteSize() {
		return spriteSize;
	}

	public TileSprite[] getSprites() {
		return sprites;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
