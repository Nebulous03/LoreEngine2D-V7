package loreEngine.core;

import org.lwjgl.glfw.GLFW;

import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Window;

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
		
		while(true) {
			
			if(GLFW.glfwWindowShouldClose(window.getGLWindowID())) break;
			
			
			
			window.update();
			
		}
		
		stop();
	}
	
	public abstract void init();

}
