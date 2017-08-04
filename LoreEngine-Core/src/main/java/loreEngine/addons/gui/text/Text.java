package loreEngine.addons.gui.text;

import java.awt.Color;
import java.util.ArrayList;

import loreEngine.core.graphics.renderer.Renderable;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Text extends Renderable {
	
	private String text;
	private Font font;
	private int size;
	private Color color;
	
	private ArrayList<Glyph> glyphs;
	
	public Text(Font font, int size, Vector3f pos, Color color, String text) {
		this.font = font;
		this.text = text;
		this.size = size;
		this.translation = Matrix4f.Translation(pos);
		this.rotation = Matrix4f.Identity();
		this.scale = Matrix4f.Identity();
		this.mesh = null;
		this.glyphs = generateGlyphs();
		this.color = color;
	}
	
	private ArrayList<Glyph> generateGlyphs() {
		
		ArrayList<Glyph> glyphs = new ArrayList<Glyph>();
		
		for(int i = 0; i < text.length(); i++) {
			glyphs.add(font.getGlyphs().get((short)text.charAt(i)));
		}
		
		return glyphs;
	}

	public Font getFont() {
		return font;
	}

	public Text setFont(Font font) {
		this.font = font;
		return this;
	}

	public int getSize() {
		return size;
	}

	public Text setSize(int size) {
		this.size = size;
		return this;
	}

	public String getText() {
		return text;
	}

	public Text setText(String text) {
		this.text = text;
		this.glyphs = generateGlyphs();
		return this;
	}

	public int getLength() {
		return text.length();
	}

	public ArrayList<Glyph> getGlyphs() {
		return glyphs;
	}

	public Color getColor() {
		return color;
	}

}
