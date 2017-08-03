package loreEngine.addons.tileMap;

public class TileSprite {

	public int ID;
	public int size;
	public int xOffset;
	public int yOffset;
	
	public TileSprite(int ID, int size, int xOffset, int yOffset) {
		this.size = size;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.ID = ID;
	}
	
}
