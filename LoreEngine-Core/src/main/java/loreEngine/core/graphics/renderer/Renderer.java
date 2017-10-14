package loreEngine.core.graphics.renderer;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.shader.Shader;

public abstract class Renderer {
	
	protected Renderer() {}
	
	public abstract void render(Renderable renderable, Camera camera, Shader shader);

	public void delete() {
		
	}
}
