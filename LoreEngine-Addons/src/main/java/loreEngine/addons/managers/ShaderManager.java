package loreEngine.addons.managers;

import java.util.HashMap;

import loreEngine.core.graphics.shader.Shader;

public class ShaderManager {
	
	public static HashMap<String, Shader> shaderMap;

	public static void init() {
		shaderMap = new HashMap<String, Shader>();
	}

	public static void addRenderer(String tag, Shader renderer) {
		shaderMap.put(tag, renderer);
	}

	@SuppressWarnings("unchecked")
	public static <R> R getRenderer(String tag) {
		return (R)shaderMap.get(tag);
	}
	
	public static void deleteRenderer(String tag) {
		shaderMap.get(tag).delete();
	}
	
}
