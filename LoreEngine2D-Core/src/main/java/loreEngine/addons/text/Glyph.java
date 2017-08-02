package loreEngine.addons.text;

import java.util.HashMap;

public class Glyph {
	
	public int id		= 0;
	public int x		= 0;
	public int y		= 0;
	public int width	= 0;
	public int height	= 0;
	public int xOffset	= 0;
	public int yOffset	= 0;
	public int xAdvance = 0;
	
	public HashMap<Short, Integer> kernings;
	
	public Glyph() {
		kernings = new HashMap<Short, Integer>();
	}

}
