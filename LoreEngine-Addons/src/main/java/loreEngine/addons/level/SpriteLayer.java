package loreEngine.addons.level;

import java.util.ArrayList;
import java.util.HashMap;

import loreEngine.addons.managers.RendererManager;
import loreEngine.addons.sprite.Sprite;
import loreEngine.addons.sprite.SpriteRenderer;

public class SpriteLayer extends Layer {
	
	public HashMap<String, Sprite> spritesMap;
	public ArrayList<Sprite> allSprites;

	public SpriteLayer() {
		super(RendererManager.get("default"));
	}

	@Override
	public void render() {
		for(Sprite sprite : allSprites)
			((SpriteRenderer)layerRenderer).render(sprite);
	}

}
