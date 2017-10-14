package sandbox2;

import loreEngine.addons.managers.TextureManager;
import loreEngine.core.Game;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.texture.Texture;
import loreEngine.core.logic.SceneManager;

public class Sandbox2 extends Game {

	public static void main(String[] args) {
		Window window = Window.createWindow("LoreEngine2D - Test", 1024, 720, DisplayType.WINDOWED);
		//Window window = Window.createWindow("LoreEngine2D - Test", 1920, 1080, DisplayType.WINDOWED);
		//Window window = Window.createWindow("LoreEngine2D - Test", 2560, 1440, DisplayType.FULLSCREEN);
		Game game = new Sandbox2(window);
		game.start();
	}
	
	public Sandbox2(Window window) {
		super(window);
	}

	@Override
	public void init() {
		TextureManager.init();
		TextureManager.register("unknown", new Texture("/textures/unknown.png"));
		SceneManager.register(new TestScene());
	}

	@Override
	public void onStart() {
		SceneManager.load("testScene", this);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void tick(int tick, int tock) {
		
	}

	@Override
	public void onStop() {
		
	}

	@Override
	public void render() {
		
	}
	
}
