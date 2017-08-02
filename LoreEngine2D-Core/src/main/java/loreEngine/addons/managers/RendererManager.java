package loreEngine.addons.managers;

import java.util.HashMap;

import loreEngine.core.graphics.renderer.Renderer;

public class RendererManager {
	
	public static HashMap<String, Renderer> renderMap;
	
	public static void init() {
		renderMap = new HashMap<String, Renderer>();
	}

	public static void addRenderer(String tag, Renderer renderer) {
		renderMap.put(tag, renderer);
	}

	@SuppressWarnings("unchecked")
	public static <R> R getRenderer(String tag) {
		return (R)renderMap.get(tag);
	}
	
	public static void deleteRenderer(String tag) {
		renderMap.get(tag).delete();
		
	}
	
}
