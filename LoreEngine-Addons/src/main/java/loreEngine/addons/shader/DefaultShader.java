package loreEngine.addons.shader;

import loreEngine.core.graphics.shader.Shader;

public class DefaultShader extends Shader {

	public DefaultShader() {
		super("/shaders/default.vs", "/shaders/default.fs");
	}

}
