package loreEngine.core.graphics.renderers;

import loreEngine.addons.text.Font;
import loreEngine.addons.text.Text;
import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Shader;

public class TextRenderer extends BasicBatchRenderer {
	
	private Font font;

	public TextRenderer(Font font, Camera camera, Shader shader) {
		super(font.getTexture(), camera, shader);
		this.font = font;
	}
	
	public void push(Text text) {
		
	}
	
	@Override
	public void push(Renderable renderable) {
		super.push(renderable);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}
