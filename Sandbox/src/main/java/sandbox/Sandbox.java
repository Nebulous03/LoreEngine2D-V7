package sandbox;

import java.text.DecimalFormat;

import loreEngine.Info;
import loreEngine.core.Game;
import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Mesh;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Renderer;
import loreEngine.core.graphics.Shader;
import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.renderers.BasicRenderer;
import loreEngine.core.logic.Input;
import loreEngine.math.Vector3f;

public class Sandbox extends Game {
	
	private Renderer renderer;
	private Renderable test;
	private Shader shader;
	private Camera camera;
	
	public Sandbox(Window window) {
		super(window);
	}

	@Override
	public void init() {
		
		renderer = new BasicRenderer();
		test = new Renderable(Mesh.Plane(), new Vector3f(0, 0, 0f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		shader = new Shader("/shaders/default.vs", "/shaders/default.fs");
		
		camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), Camera.CAMERA_PERSPECTIVE, window.getWidth(), window.getHeight(), 90.0f);
		camera.move(new Vector3f(0, 0, 1.0f), 1.0f);
		
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
	
	DecimalFormat df = new DecimalFormat("0.000"); 
	
	@Override
	public void tick(int tick, int tock) {
		window.setTitle("Sandbox - LoreEngine " + Info.VERSION + " | FPS: " + getActiveFPS()+ " | MS: " + df.format(getActiveMS()));
	}

	@Override
	public void onStop() {
		
	}

	@Override
	public void render() {
		renderer.render(test, camera, shader);
	}
	
	public static void main(String[] args) {
		Window window = Window.createWindow("LoreEngine2D - Test", 1024, 720, DisplayType.WINDOWED);
		Game game = new Sandbox(window);
		game.start();
	}

}
