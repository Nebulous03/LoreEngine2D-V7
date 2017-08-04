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
	private Camera guiCamera;
	private TileMap map;
	
	private Sprite player;
	
	private Text fpsText;
	private Text cameraPosText;
	
	public Sandbox(Window window) {
		super(window);
	}

	@Override
	public void init() {
		
		Log.setGlobalLogLevel(LogLevel.INFO);
		
		setTPS(10);
		
		SpriteMapManager.init();
		TextureManager.init();
		ShaderManager.init();
		RendererManager.init();
		
		TextureManager.register("unknown1", new Texture("/textures/unknown.png"));
		TextureManager.register("crate", new Texture("/textures/crate.png"));
		TextureManager.register("testMap", new Texture("/textures/testSpriteMap.png"));
		TextureManager.register("minecraft", new Texture("/textures/minecraft.png"));
		TextureManager.register("arial", new Texture("/fonts/arial.png"));
		TextureManager.register("comicSans", new Texture("/fonts/comicSansMS.png"));
		
		ShaderManager.register("default", new Shader("/shaders/default.vs", "/shaders/default.fs"));
		
		camera = new SimpleCamera(window, 0, 0, -10.0f);
		guiCamera = new SimpleCamera(window, 0, 0, -20.0f);
		
		RendererManager.register("spriteRenderer", new SpriteRenderer(camera, ShaderManager.get("default")));
		RendererManager.register("tileMapRenderer", new TileMapRenderer(camera, ShaderManager.get("default")));
		RendererManager.register("textRenderer", new TextRenderer(guiCamera, ShaderManager.get("default")));
		
		SpriteMapManager.register("testMap", new SpriteMap(TextureManager.get("testMap"), 16));
		SpriteMapManager.register("minecraftMap", new SpriteMap(TextureManager.get("minecraft"), 16));
		
		player = new Sprite(TextureManager.get("unknown1"), 0, 0);
		
		Tile STONE	= new Tile(1,0);
		Tile DIRT	= new Tile(2,0);
		Tile GRASS	= new Tile(3,0);
		Tile PLANK	= new Tile(4,0);

		map = new TileMap(SpriteMapManager.get("minecraftMap"), 1024, 1024).enableCulling(true).populate(STONE);
		map.set(3, 3, PLANK);
		
		fpsText = new Text(new Font("/fonts/comicSansMS.fnt", TextureManager.get("comicSans")), 50, new Vector3f(0,0,0), Color.WHITE, "FPS: " + getActiveFPS());
		fpsText.setTranslation(Matrix4f.Translation(new Vector3f(-35, 20, 0)));
		
		cameraPosText = new Text(new Font("/fonts/comicSansMS.fnt", TextureManager.get("comicSans")), 60, new Vector3f(0,0,0), Color.WHITE, "Camera:");
		cameraPosText.setTranslation(Matrix4f.Translation(new Vector3f(-35, 18.5f, 0)));
		
	}

	@Override
	public void onStart() {
		
	}
	
	private float moveSpeed = 5.0f;
	
	@Override
	public void update(float delta) {
		
		if(Input.isKeyHeld(Input.KEY_W)) {
			camera.move(new Vector3f(0, 1, 0), moveSpeed * delta);
			player.move(0, moveSpeed * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_S)) {
			camera.move(new Vector3f(0, -1, 0), moveSpeed * delta);
			player.move(0, -moveSpeed * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_A)) {
			camera.move(new Vector3f(-1, 0, 0), moveSpeed * delta);
			player.move(-moveSpeed * delta, 0);
		}
		
		if(Input.isKeyHeld(Input.KEY_D)) {
			camera.move(new Vector3f(1, 0, 0), moveSpeed * delta);
			player.move(moveSpeed * delta, 0);
		}
		
	}
	
	DecimalFormat df = new DecimalFormat("0.00000"); 
	
	@Override
	public void tick(int tick, int tock) {
		window.setTitle("Sandbox - LoreEngine " + Info.VERSION + " | FPS: " + getActiveFPS()+ " | MS: " + df.format(getActiveMS()));
		fpsText.setText("FPS: " + getActiveFPS());
		cameraPosText.setText("Camera: " + camera.getPosition().x + ", " + camera.getPosition().y);
	}

	@Override
	public void onStop() {
		
	}
	
	@Override
	public void render() {
		((TileMapRenderer)RendererManager.get("tileMapRenderer")).render(map);
		((SpriteRenderer)RendererManager.get("spriteRenderer")).render(player);
		((TextRenderer)RendererManager.get("textRenderer")).render(fpsText);
		((TextRenderer)RendererManager.get("textRenderer")).render(cameraPosText);
	}
	
	public static void main(String[] args) {
		//Window window = Window.createWindow("LoreEngine2D - Test", 1024, 720, DisplayType.WINDOWED);
		Window window = Window.createWindow("LoreEngine2D - Test", 1920, 1080, DisplayType.WINDOWED);
		//Window window = Window.createWindow("LoreEngine2D - Test", 2560, 1440, DisplayType.FULLSCREEN);
		Game game = new Sandbox(window);
		game.start();
	}

}
