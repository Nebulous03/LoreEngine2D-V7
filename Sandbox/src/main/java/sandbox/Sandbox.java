package sandbox;

import java.awt.Color;
import java.text.DecimalFormat;

import loreEngine.Info;
import loreEngine.addons.text.Font;
import loreEngine.addons.text.Text;
import loreEngine.core.Game;
import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Mesh;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Shader;
import loreEngine.core.graphics.Texture;
import loreEngine.core.graphics.Texture.Filter;
import loreEngine.core.graphics.Texture.Wrap;
import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.renderers.BasicRenderer;
import loreEngine.core.graphics.renderers.TextRenderer;
import loreEngine.core.graphics.renderers.BasicBatchRenderer;
import loreEngine.core.logic.Input;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

@SuppressWarnings("unused")
public class Sandbox extends Game {
	
	private BasicRenderer basicRenderer;
	private BasicBatchRenderer batchRenderer;
	private TextRenderer textRenderer;
	private Texture texture;
	private Renderable test;
	private Shader shader;
	private Camera camera;
	private Camera guiCamera;
	
	private Font arial;
	private Font comicSans;
	private Text text;
	
	private final int BATCH_GRID = 16;
	
	public Sandbox(Window window) {
		super(window);
	}

	@Override
	public void init() {
		
		Log.setGlobalLogLevel(LogLevel.INFO);
		
		shader = new Shader("/shaders/default.vs", "/shaders/default.fs");
		camera = new Camera(new Vector3f(0, 0, 2.0f), new Vector3f(0, 0, 0), Camera.CAMERA_PERSPECTIVE, window.getWidth(), window.getHeight(), 90.0f);
		guiCamera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0), Camera.CAMERA_PERSPECTIVE, window.getWidth(), window.getHeight(), 90.0f);
		//camera = new Camera(new Vector3f(150.0f, 50.0f, 50.0f), new Vector3f(0, 0, 0), Camera.CAMERA_PERSPECTIVE, window.getWidth(), window.getHeight(), 90.0f);
		
		texture = new Texture("/textures/unknown.png", Wrap.REPEAT, Filter.NEAREST);
		test = new Renderable(Mesh.Plane(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), texture, Color.CYAN);
		arial = new Font("/fonts/Arial.fnt", "/fonts/Arial.png");
		comicSans = new Font("/fonts/ComicSansMS.fnt", "/fonts/ComicSansMS.png");

		basicRenderer = new BasicRenderer();
		batchRenderer = new BasicBatchRenderer(texture, camera, shader);
		textRenderer = new TextRenderer(guiCamera, shader);
		
		text = (Text)new Text(comicSans, 32, new Vector3f(0, 0, 0), Color.WHITE, "FPS: " + getActiveFPS())
				.setScale(Matrix4f.Scale(new Vector3f(0.05f, 0.05f, 1.0f)))
				.setTranslation(Matrix4f.Translation(new Vector3f(-32, 18, 0)));
		
	}

	@Override
	public void onStart() {
		
	}
	
	@Override
	public void update(float delta) {
		
		if(Input.isKeyHeld(Input.KEY_W)) {
			camera.move(new Vector3f(0, 1, 0), 1.0f * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_S)) {
			camera.move(new Vector3f(0, -1, 0), 1.0f * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_A)) {
			camera.move(new Vector3f(-1, 0, 0), 1.0f * delta);
		}
		
		if(Input.isKeyHeld(Input.KEY_D)) {
			camera.move(new Vector3f(1, 0, 0), 1.0f * delta);
		}
		
	}
	
	DecimalFormat df = new DecimalFormat("0.00000"); 
	
	@Override
	public void tick(int tick, int tock) {
		window.setTitle("Sandbox - LoreEngine " + Info.VERSION + " | FPS: " + getActiveFPS()+ " | MS: " + df.format(getActiveMS()));
		text.setText("FPS: " + getActiveFPS());
	}

	@Override
	public void onStop() {
		
	}
	
	boolean useBatch = true;
	Matrix4f translation = Matrix4f.Translation(new Vector3f(0, 0, 0));
	
	@Override
	public void render() {
		
		if(useBatch) {
			
			batchRenderer.begin();

			for(int y = 0; y < BATCH_GRID; y++) {
				for(int x = 0; x < BATCH_GRID; x++) {
					
					translation.getElements()[0 + 3 * 4] = x;
					translation.getElements()[1 + 3 * 4] = y;
					
					batchRenderer.pushTransform(translation);
					batchRenderer.push(test);
					batchRenderer.popTransform();
				}
			}
			
			batchRenderer.end();
			
		} else {
			
			for(int y = 0; y < BATCH_GRID; y++) {
				for(int x = 0; x < BATCH_GRID; x++) {
					
					translation.getElements()[0 + 3 * 4] = x;
					translation.getElements()[1 + 3 * 4] = y;
					
					basicRenderer.render(test.setTranslation(translation), camera, shader);
				}
			}
			
		}
		
		textRenderer.render(text);
		
	}
	
	public static void main(String[] args) {
		//Window window = Window.createWindow("LoreEngine2D - Test", 1024, 720, DisplayType.WINDOWED);
		Window window = Window.createWindow("LoreEngine2D - Test", 1920, 1080, DisplayType.WINDOWED);
		Game game = new Sandbox(window);
		game.start();
	}

}
