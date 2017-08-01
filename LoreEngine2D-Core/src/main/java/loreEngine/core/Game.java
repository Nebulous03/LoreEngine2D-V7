package loreEngine.core;

import org.lwjgl.glfw.GLFW;

import loreEngine.Info;
import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Mesh;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Renderer;
import loreEngine.core.graphics.Shader;
import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.renderers.BasicRenderer;
import loreEngine.math.Vector3f;
import loreEngine.utils.Console;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public abstract class Game {
	
	protected Window window;
	protected double tps;
	
	public Game() {
		this.window = Window.createWindow("LoreEngine2D - Test", 640, 480, DisplayType.WINDOWED, true);
	}
	
	public Game(Window window) {
		this.window = window;
	}

	public void start() {
		init();
		onStart();
		run();
	}
	
	public void stop() {
		onStop();
		GLFW.glfwTerminate();
		System.exit(0);
	}

	private void run() {
		
		Console.printRaw
		(
			"------------------------------------------------\n" +
			"  MADE IN LORE ENGINE " + Info.VERSION + "\n" +
			"  COPYWRITE BEN RATCLIFF (NEBULOUSDEV) 2017" + "\n" +
			"  UNDER THE APACHE 2.0 LICENCE" + "\n" +
			"------------------------------------------------\n"
		);
		
		window.printGLStats();
		
		Console.printRaw
		(
			"------------------------------------------------\n"
		);
		
		Log.log(LogLevel.INFO, "This is a logged error!");
		
		Renderer renderer = new BasicRenderer();
		Renderable test = new Renderable(Mesh.Plane(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		Shader shader = new Shader("/shaders/default.vs", "/shaders/default.fs");
		
		Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), Camera.CAMERA_PERSPECTIVE, window.getWidth(), window.getHeight(), 90.0f);
		camera.move(new Vector3f(1.0f, 0, -1.0f), 1.0f);

		while(true) {
			
			window.clear();
			
			if(GLFW.glfwWindowShouldClose(window.getGLWindowID())) break;
			
			renderer.render(test, camera, shader);
			
			window.update();
			
		}
		
		stop();
	}
	
	public abstract void init();
	
	public abstract void onStart();
	
	public abstract void onStop();

}
