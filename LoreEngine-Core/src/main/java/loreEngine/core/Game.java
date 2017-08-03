package loreEngine.core;

import org.lwjgl.glfw.GLFW;

import loreEngine.Info;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Window;
import loreEngine.core.logic.Input;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;
import loreEngine.utils.Time;

public abstract class Game {
	
	public enum GameStatus {
		RUNNING,
		PAUSED,
		STOPPED
	}
	
	protected Window window;
	
	protected boolean fpsCapped = false;
	
	protected int tps = 1;
	protected int fps = 200;
	
	public int activeFPS;
	public double activeMS;
	
	protected GameStatus status;
	
	public Game() {
		this.window = Window.createWindow("LoreEngine2D - Test", 640, 480, DisplayType.WINDOWED, true);
		Info.printOpener(this);
	}
	
	public Game(Window window) {
		this.window = window;
		Info.printOpener(this);
	}

	public void start() {
		Log.logln(LogLevel.INFO, "Game is initializing...");
		Input.init(getWindow());
		init();
		Log.logln(LogLevel.INFO, "Game is starting...");
		onStart();
		Log.logln(LogLevel.INFO, "Game is running...");
		run();
	}
	
	public void stop() {
		Log.logln(LogLevel.INFO, "Game is stopping...");
		onStop();
		GLFW.glfwTerminate();
		Log.logln(LogLevel.INFO, "Game has stopped.");
		System.exit(0);
	}

	private void run() {
		
		status = GameStatus.RUNNING;
		
		double oldTime = 0.0;
		double newTime = 0.0;
		double delta   = 0.0;
		
		double startTime = Time.getTimeNanoSeconds();
		
		double frameTime  = 0.0;
		double tickTime = 0.0;
		
		double msStart = 0.0;
		double msEnd   = 0.0;
		
		int frames = 0;
		int tick = 0;
		
		double elapsedTick = 0.0;
		double elapsedFrame = 0.0;
		
		while(true) {
			
			if(GLFW.glfwWindowShouldClose(window.getGLWindowID())) break;
			
			oldTime = newTime;
			newTime = Time.getTimeNanoSeconds();
			delta	= (newTime - oldTime) / 1000000000.0;
			
			elapsedFrame = ((newTime - startTime) / 1000000000.0) - frameTime;
			
			if(elapsedFrame > 1.0) {
				frameTime += 1.0;
				activeFPS = (int)frames;
				frames = 0;
			}
			
			elapsedTick = ((newTime - startTime) / 1000000000.0) - tickTime;
			
			if(elapsedTick > 1.0 / (double)tps) {
				tickTime += 1.0 / (double)tps;
				tick++;
				tick(tick, tps);
				if(tick >= tps) tick = 0;
			}
			
			update((float)delta);
			Input.update();
			
			msStart = Time.getTimeMiliSeconds();
			
			if(!fpsCapped) {
				window.clear();
				render();
				frames++;				
			} else {
				if(frames < fps) {
					window.clear();
					render();
					frames++;	
				}
			}
			
			msEnd = Time.getTimeMiliSeconds();
			activeMS = msEnd - msStart;
			
			window.update();
		}
		
		stop();
	}
	
	public abstract void init();
	
	public abstract void onStart();
	
	public abstract void update(float delta);
	
	public abstract void tick(int tick, int tock);
	
	public abstract void onStop();
	
	public abstract void render();
	
	public Window getWindow() {
		return window;
	}
	
	public int getActiveFPS() {
		return activeFPS;
	}
	
	public double getActiveMS() {
		return activeMS;
	}
	
	public int getTPSCap() {
		return tps;
	}
	
	public void setTPS(int tps) {
		this.tps = tps;
	}
	
	public int getFPSCap() {
		return fps;
	}
	
	public void fpsCapped(boolean fpsCapped) {
		this.fpsCapped = fpsCapped;
	}
	
	public boolean isFPSCapped() {
		return fpsCapped;
	}
	
	public void setFPS(int fps) {
		this.fps = fps;
	}

}
