package loreEngine.addons.text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import loreEngine.core.graphics.Shader;
import loreEngine.core.graphics.Texture;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class Font {
	
	private String name;
	private int lineHeight;
	private int base;
	private int scaleW;
	private int scaleH;
	
	private Texture texture;
	
	private HashMap<Short, Glyph> glyphs;
	
	public Font(String fontFile, String fontImageFile) {
		glyphs = loadGliphs(fontFile);
		this.texture = new Texture(fontImageFile);
		Log.logln(LogLevel.INFO, "Font succesfully created: " + fontFile);
	}
	
	private HashMap<Short, Glyph> loadGliphs(String fontFile) {
		
		final int headerSize = 4;
		
		HashMap<Short, Glyph> glyphs = new HashMap<Short, Glyph>();
		String fileString = "";

		fileString = readFile(fontFile);
		
		String[] lines = fileString.split("\n");
		
		name 		= 				   lines[0].split("\\s+")[1].split("=")[1];
		lineHeight	= Integer.parseInt(lines[1].split("\\s+")[1].split("=")[1]);
		base		= Integer.parseInt(lines[1].split("\\s+")[2].split("=")[1]);
		scaleW		= Integer.parseInt(lines[1].split("\\s+")[3].split("=")[1]);
		scaleH		= Integer.parseInt(lines[1].split("\\s+")[4].split("=")[1]);
		
		int kerningStart = 0;
		
		for(int i = headerSize; i < lines.length - headerSize; i++) {
			
			String[] data = lines[i].split("\\s+");
			
			if(data[0].equals("kernings")){ kerningStart = i + 1; break; }
			
			Glyph glyph = new Glyph();

			glyph.id		= Integer.parseInt(data[1].split("=")[1]);
			glyph.x			= Integer.parseInt(data[2].split("=")[1]);
			glyph.y			= Integer.parseInt(data[3].split("=")[1]);
			glyph.width		= Integer.parseInt(data[4].split("=")[1]);
			glyph.height	= Integer.parseInt(data[5].split("=")[1]);
			glyph.xOffset	= Integer.parseInt(data[6].split("=")[1]);
			glyph.yOffset	= Integer.parseInt(data[7].split("=")[1]);
			glyph.xAdvance  = Integer.parseInt(data[8].split("=")[1]);
			
			glyphs.put((short)glyph.id, glyph);
			
		}
		
		for(int i = kerningStart; i < lines.length - headerSize; i++) {
			
			String[] data = lines[i].split("\\s+");
			
			int first   = Integer.parseInt(data[1].split("=")[1]);
			int second  = Integer.parseInt(data[2].split("=")[1]);
			int ammount = Integer.parseInt(data[3].split("=")[1]);
			
			glyphs.get((short)first).kernings.put((short)second, ammount);
		}
		
		return glyphs;
	}
	
	public static String readFile(String filename){
		
		StringBuilder source = new StringBuilder();
		BufferedReader reader = null;
		
		try{
			InputStream input = Shader.class.getResourceAsStream(filename);
			reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while((line = reader.readLine()) != null)
				source.append(line).append("\n");
			reader.close();
		} catch (Exception e){
			Log.logln(LogLevel.ERROR, "ERROR! - Failed to read file! [" + filename + "]");
			e.printStackTrace();
		}
		
		return source.toString();
	}

	public String getName() {
		return name;
	}

	public int getLineHeight() {
		return lineHeight;
	}

	public int getBase() {
		return base;
	}

	public int getScaleW() {
		return scaleW;
	}

	public int getScaleH() {
		return scaleH;
	}

	public Texture getTexture() {
		return texture;
	}

	public HashMap<Short, Glyph> getGlyphs() {
		return glyphs;
	}

}
