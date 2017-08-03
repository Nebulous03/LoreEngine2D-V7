package loreEngine.addons.managers;

import java.util.HashMap;

import loreEngine.core.graphics.renderer.Renderer;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class RendererManager {
	
	public static HashMap<String, Renderer> renderMap;
	
	public static void init() {
		renderMap = new HashMap<String, Renderer>();
	}

	public static void register(String tag, Renderer renderer) {
		if(renderMap == null)
			Log.logln(LogLevel.ERROR, "RendererManager has not been initialized! Must run RenderManager.init() before registering items!");
		else 
			renderMap.put(tag, renderer);
	}

	@SuppressWarnings("unchecked")
	public static <R> R get(String tag) {
		
		if(renderMap == null) {
			Log.logln(LogLevel.ERROR, "RendererManager has not been initialized! Must run RenderManager.init() before getting items!");
			return null;
		}
		
		if(!renderMap.containsKey(tag)) {
			Log.logln(LogLevel.ERROR, "Renderer '" + tag + "' is not registered in RenderManager");
			return null;
		}
		
		return (R)renderMap.get(tag);
	}
	
	public static void delete(String tag) {
		
		if(renderMap == null){
			Log.logln(LogLevel.ERROR, "RendererManager has not been initialized! Must run RendererManager.init() before deleting items!");
			return;
		}
		
		if(!renderMap.containsKey(tag)){
			Log.logln(LogLevel.ERROR, "Renderer '" + tag + "' is not registered in RendererManager");
			return;
		} 
		
		else {
			renderMap.get(tag).delete();
			renderMap.remove(tag);
		}
	}
	
}
