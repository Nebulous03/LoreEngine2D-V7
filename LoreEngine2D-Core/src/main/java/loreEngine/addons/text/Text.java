package loreEngine.addons.text;

import loreEngine.core.graphics.Renderable;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Text extends Renderable {
	
	private String text;
	private Font font;
	private int size;
	
	public Text(Font font, int size, Vector3f pos, String text) {
		this.text = text;
		this.size = size;
		this.translation = Matrix4f.Translation(pos);
		this.rotation = Matrix4f.Identity();
		this.scale = Matrix4f.Identity();
		this.mesh = null;
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
		return this;
	}

}
