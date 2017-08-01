package loreEngine;

import loreEngine.core.Game;
import loreEngine.utils.Console;

public class Info {
	
	public static final String NAME    = "LORE ENGINE";
	public static final String VERSION = "v0.2.2";

	public static final void printOpener(Game game) {
		Console.printRaw
		(
			"------------------------------------------------\n" +
			"  MADE IN " + NAME + " " + VERSION + "\n" +
			"  COPYWRITE BEN RATCLIFF (NEBULOUSDEV) 2017" + "\n" +
			"  UNDER THE APACHE 2.0 LICENCE" + "\n" +
			"------------------------------------------------\n"
		);
		
		game.getWindow().printGLStats();
		
		Console.printRaw
		(
			"------------------------------------------------\n"
		);
	}
	
}
