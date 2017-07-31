package sandbox;

import loreEngine.core.Game;
import loreEngine.core.graphics.DisplayType;
import loreEngine.core.graphics.Window;

public class Sandbox extends Game {
	
	public static void main(String[] args) {
		Window window = Window.createWindow("LoreEngine2D - Test", 1024, 720, DisplayType.WINDOWED);
		Game game = new Sandbox(window);
		game.start();
	}
	
	public Sandbox(Window window) {
		super(window);
	}

	@Override
	public void init() {
		
	}

}
