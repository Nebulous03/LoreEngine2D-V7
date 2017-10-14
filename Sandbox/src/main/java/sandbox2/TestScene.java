package sandbox2;

import lore.math.Vector3f;
import loreEngine.addons.managers.TextureManager;
import loreEngine.addons.sprite.Sprite;
import loreEngine.core.Game;
import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.layer.Layer;
import loreEngine.core.graphics.layer.Scene;
import loreEngine.core.graphics.renderer.BasicRenderer;
import loreEngine.core.graphics.renderer.Renderer;
import loreEngine.core.graphics.shader.Shader;

public class TestScene extends Scene {

	public TestScene() {
		super("testScene");
	}

	@Override
	public void create(Game game) {
		Renderer r1 = new BasicRenderer();
		Camera c1 = new Camera(game.getWindow(), new Vector3f(0, 0, -10), new Vector3f(0, 0, 0), Camera.CAMERA_PERSPECTIVE, 90.0f);
		Shader s1 = new Shader("/shaders/default.vs", "/shaders/default.fs");
		addLayer(new Layer(r1, c1, s1) {
			
			@Override
			public void dispose() {
				
			}
			
			@Override
			public void create() {
				add(new Sprite(TextureManager.get("unknown"), 0, 0));
			}
			
		});
	}

	@Override
	public void onLoad(Game game) {
		
	}

	@Override
	public void onUnload(Game game) {
		
	}

	@Override
	public void dispose(Game game) {
		
	}
	
}
