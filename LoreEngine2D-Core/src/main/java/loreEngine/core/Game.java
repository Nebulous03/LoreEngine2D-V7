package loreEngine.core;

import org.lwjgl.glfw.GLFW;

import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Mesh;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Renderer;
import loreEngine.core.graphics.Shader;
import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.renderers.BasicRenderer;
import loreEngine.math.Vector3f;

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
		run();
	}
	
	public void stop() {
		GLFW.glfwTerminate();
		System.exit(0);
	}

	private void run() {
		
		window.printGLStats();
		
		Renderer renderer = new BasicRenderer();
		Renderable test = new Renderable(Mesh.Plane(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		Shader shader = new Shader("/shaders/default.vs", "/shaders/default.fs");

		while(true) {
			
			window.clear();
			
			if(GLFW.glfwWindowShouldClose(window.getGLWindowID())) break;
			
			renderer.render(test, null, shader);
			
			window.update();
			
		}
		
		stop();
	}
	
	public abstract void init();

}
