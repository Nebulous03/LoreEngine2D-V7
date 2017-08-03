package loreEngine.addons.sprite;

import loreEngine.core.graphics.renderer.Renderable;

public class Sprite extends Renderable {

	public int size;
	public int xOffset;
	public int yOffset;
	
	public Sprite(int size, int xOffset, int yOffset) {
		this.size = size;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
