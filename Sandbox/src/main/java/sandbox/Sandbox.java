package sandbox;

import java.awt.Color;
import java.text.DecimalFormat;

import loreEngine.Info;
import loreEngine.addons.camera.SimpleCamera;
import loreEngine.addons.gui.text.Font;
import loreEngine.addons.gui.text.Text;
import loreEngine.addons.gui.text.TextRenderer;
import loreEngine.addons.managers.RendererManager;
import loreEngine.addons.managers.ShaderManager;
import loreEngine.addons.managers.SpriteMapManager;
import loreEngine.addons.managers.TextureManager;
import loreEngine.addons.shader.DefaultShader;
import loreEngine.addons.sprite.Sprite;
import loreEngine.addons.sprite.SpriteMap;
import loreEngine.addons.sprite.SpriteRenderer;
import loreEngine.addons.tileMap.Tile;
import loreEngine.addons.tileMap.TileMap;
import loreEngine.addons.tileMap.TileMapRenderer;
import loreEngine.addons.tileMap.TileSprite;
import loreEngine.core.Game;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.renderer.BasicBatchRenderer;
import loreEngine.core.graphics.renderer.BasicRenderer;
import loreEngine.core.graphics.renderer.Renderable;
import loreEngine.core.graphics.shader.Shader;
import loreEngine.core.graphics.texture.Texture;
import loreEngine.core.graphics.texture.Texture.Filter;
import loreEngine.core.graphics.texture.Texture.Wrap;
import loreEngine.core.graphics.vertex.Mesh;
import loreEngine.core.logic.Input;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

@SuppressWarnings("unused")
public class Sandbox extends Game {
	
	private Camera camera;
	private TileMap map;
	
	private Sprite player;
	
	public Sandbox(Window window) {
		super(window);
	}

	@Override
	public void init() {
		
		Log.setGlobalLogLevel(LogLevel.INFO);
		
		SpriteMapManager.init();
		TextureManager.init();
		ShaderManager.init();
		RendererManager.init();
		
		TextureManager.register("unknown1", new Texture("/textures/unknown.png"));
		TextureManager.register("crate", new Texture("/textures/crate.png"));
		TextureManager.register("testMap", new Texture("/textures/testSpriteMap.png"));
		TextureManager.register("minecraft", new Texture("/textures/minecraft.png"));
		TextureManager.register("arial", new Texture("/fonts/arial.png"));
		TextureManager.register("comicSans", new Texture("/fonts/comicSansMs.png"));
		
		ShaderManager.register("default", new Shader("/shaders/default.vs", "/shaders/default.fs"));
		
		camera = new SimpleCamera(window, 0, 0, 10.0f);
		
		RendererManager.register("spriteRenderer", new SpriteRenderer(camera, ShaderManager.get("default")));
		RendererManager.register("tileMapRenderer", new TileMapRenderer(camera, ShaderManager.get("default")));
		
		SpriteMapManager.register("testMap", new SpriteMap(TextureManager.get("testMap"), 16));
		SpriteMapManager.register("minecraftMap", new SpriteMap(TextureManager.get("minecraft"), 16));
		
		player = new Sprite(TextureManager.get("unknown1"), 0, 0);
		
		Tile STONE	= new Tile(1,0);
		Tile DIRT	= new Tile(2,0);
		Tile GRASS	= new Tile(3,0);
		Tile PLANK	= new Tile(4,0);

		map = new TileMap(SpriteMapManager.get("minecraftMap"), 32, 32).populate(STONE);
		map.set(3, 3, PLANK);
		
	}

	@Override
	public void onStart() {
		
	}
	
	@Override
	public void update(float delta) {
		
		if(Input.isKeyHeld(Input.KEY_W)) {
			camera.move(new Vector3f(0, 1, 0), 5.0f * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_S)) {
			camera.move(new Vector3f(0, -1, 0), 5.0f * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_A)) {
			camera.move(new Vector3f(-1, 0, 0), 5.0f * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_D)) {
			camera.move(new Vector3f(1, 0, 0), 5.0f * delta);
		}
		
	}
	
	DecimalFormat df = new DecimalFormat("0.00000"); 
	
	@Override
	public void tick(int tick, int tock) {
		window.setTitle("Sandbox - LoreEngine " + Info.VERSION + " | FPS: " + getActiveFPS()+ " | MS: " + df.format(getActiveMS()));
		//text.setText("FPS: " + getActiveFPS());
	}

	@Override
	public void onStop() {
		
	}
	
	@Override
	public void render() {
		((TileMapRenderer)RendererManager.get("tileMapRenderer")).render(map);
		((SpriteRenderer)RendererManager.get("spriteRenderer")).render(player);
	}
	
	public static void main(String[] args) {
		//Window window = Window.createWindow("LoreEngine2D - Test", 1024, 720, DisplayType.WINDOWED);
		Window window = Window.createWindow("LoreEngine2D - Test", 1920, 1080, DisplayType.WINDOWED);
		//Window window = Window.createWindow("LoreEngine2D - Test", 2560, 1440, DisplayType.FULLSCREEN);
		Game game = new Sandbox(window);
		game.start();
	}

}
