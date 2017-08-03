package loreEngine.addons.level;

import loreEngine.addons.managers.RendererManager;
import loreEngine.addons.tileMap.TileMap;
import loreEngine.addons.tileMap.TileMapRenderer;

public class TileMapLayer extends Layer {
	
	private TileMap map;
	
	public TileMapLayer(TileMap map) {
		super(RendererManager.get("tileRenderer"));
		this.map = map;
	}

	@Override
	public void render() {
		((TileMapRenderer)layerRenderer).render(map);
	}

}
