package loreEngine.addons.sprite;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.renderer.BasicRenderer;
import loreEngine.core.graphics.shader.Shader;

public class SpriteRenderer extends BasicRenderer{
	
	public SpriteRenderer(Camera camera, Shader shader) {
		super(camera, shader);
	}

	public void render(Sprite sprite) {
		super.render(sprite);
	}

}
