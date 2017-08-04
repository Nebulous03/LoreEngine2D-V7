package loreEngine.addons.level;

import loreEngine.core.graphics.renderer.Renderer;

public abstract class Layer {
	
	protected Renderer layerRenderer;
	
	public Layer(Renderer layerRenderer) {
		this.layerRenderer = layerRenderer;
	}
	
	public abstract void render();

}
