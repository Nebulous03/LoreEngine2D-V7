package loreEngine.addons.managers;

import java.util.HashMap;

import loreEngine.core.graphics.texture.Texture;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class TextureManager {
	
	public static HashMap<String, Texture> textureMap;
	
	public static void init() {
		textureMap = new HashMap<String, Texture>();
	}
	
	public static void register(String tag, Texture texture) {
		if(textureMap == null)
			Log.logln(LogLevel.ERROR, "TextureManager has not been initialized! Must run TextureManager.init() before registering items!");
		else 
			textureMap.put(tag, texture);
	}
	
	public static Texture get(String tag) {
		
		if(textureMap == null) {
			Log.logln(LogLevel.ERROR, "TextureManager has not been initialized! Must run TextureManager.init() before getting items!");
			return null;
		}
		
		if(!textureMap.containsKey(tag)) {
			Log.logln(LogLevel.ERROR, "Texture '" + tag + "' is not registered in TextureManager");
			return null;
		}
		
		return textureMap.get(tag);
	}

	public static void delete(String tag) {
		
		if(textureMap == null){
			Log.logln(LogLevel.ERROR, "TextureManager has not been initialized! Must run TextureManager.init() before deleting items!");
			return;
		}
		
		if(!textureMap.containsKey(tag)){
			Log.logln(LogLevel.ERROR, "Texture '" + tag + "' is not registered in TextureManager");
			return;
		} 
		
		else {
			textureMap.remove(tag);
		}
	}
	
}
