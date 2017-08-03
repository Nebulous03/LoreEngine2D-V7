package loreEngine.addons.tileMap;

import loreEngine.addons.sprite.SpriteMap;

public class TileMap {
	
	private int width;
	private int height;
	
	private Tile[] tileMap;
	
	private SpriteMap spriteMap;
	
	public TileMap(SpriteMap spriteMap, int width, int height) {
		this.spriteMap = spriteMap;
		this.width = width;
		this.height = height;
		this.tileMap = new Tile[width * height];
		for(int i = 0; i < tileMap.length; i++) {
			tileMap[i] = new Tile(0,0);
		}
	}
	
	public TileMap populate(int spriteX, int spriteY) {
		for(int i = 0; i < tileMap.length; i++)
			tileMap[i] = new Tile(spriteX, spriteY);
		return this;
	}
	
	public TileMap populate(Tile tile) {
		for(int i = 0; i < tileMap.length; i++)
			tileMap[i] = new Tile(tile.texX, tile.texY);
		return this;
	}
	
	public TileMap set(int x, int y, Tile tile) {
		tileMap[x + y * width].texX = tile.texX;
		tileMap[x + y * width].texY = tile.texY;
		return this;
	}
	
	public Tile get(int x, int y) {
		return tileMap[x + y * width];
	}

	public Tile[] getTileMap() {
		return tileMap;
	}

	public TileMap setTileMap(Tile[] tileMap) {
		this.tileMap = tileMap;
		return this;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public SpriteMap getSpriteMap() {
		return spriteMap;
	}

}
