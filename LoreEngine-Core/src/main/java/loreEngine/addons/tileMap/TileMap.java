package loreEngine.addons.tileMap;

import loreEngine.addons.sprite.SpriteMap;

public class TileMap {
	
	private int width;
	private int height;
	
	private int cullWidth;
	private int cullHeight;
	
	private boolean cullingEnabled;
	
	private Tile[] tileMap;
	
	private SpriteMap spriteMap;
	
	public TileMap(SpriteMap spriteMap, int width, int height) {
		this(spriteMap, width, height, 16, 16);
	}
	
	public TileMap(SpriteMap spriteMap, int width, int height, int cullWidth, int cullHeight) {
		this.spriteMap = spriteMap;
		this.width = width;
		this.height = height;
		this.cullWidth = cullWidth;
		this.cullHeight = cullHeight;
		this.cullingEnabled = false;
		this.tileMap = new Tile[width * height];
		//for(int i = 0; i < tileMap.length; i++) {
		//	tileMap[i] = new Tile(0,0);
		//}
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
		get(x,y).texX = tile.texX;
		get(x,y).texY = tile.texY;
		return this;
	}
	
	public Tile get(int x, int y) {
		return ((x <= width -1 && y <= height - 1) && (x >= 0 && y >= 0)) ? tileMap[x + (y * width)] : null;
	}
	
	public TileMap setCullSize(int width, int height) {
		this.cullWidth = width;
		this.cullHeight = height;
		return this;
	}
	
	public TileMap enableCulling(boolean culling) {
		this.cullingEnabled = culling;
		return this;
	}
	
	public boolean isCullingEnabled() {
		return cullingEnabled;
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

	public int getCullWidth() {
		return cullWidth;
	}

	public int getCullHeight() {
		return cullHeight;
	}

}
