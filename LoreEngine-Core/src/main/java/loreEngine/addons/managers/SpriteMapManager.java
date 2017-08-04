package loreEngine.addons.managers;

import java.util.HashMap;

import loreEngine.addons.sprite.SpriteMap;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class SpriteMapManager {

	public static HashMap<String, SpriteMap> spriteMap;

	public static void init() {
		spriteMap = new HashMap<String, SpriteMap>();
	}

	public static void register(String tag, SpriteMap map) {
		if(spriteMap == null)
			Log.logln(LogLevel.ERROR, "SpriteMapManager has not been initialized! Must run SpriteMapManager.init() before registering items!");
		else 
			spriteMap.put(tag, map);
	}

	public static SpriteMap get(String tag) {
		
		if(spriteMap == null) {
			Log.logln(LogLevel.ERROR, "SpriteMapManager has not been initialized! Must run SpriteMapManager.init() before getting items!");
			return null;
		}
		
		if(!spriteMap.containsKey(tag)) {
			Log.logln(LogLevel.ERROR, "SpriteMap '" + tag + "' is not registered in SpriteMapManager");
			return null;
		}
		
		return spriteMap.get(tag);
	}
	
	public static void delete(String tag) {
		
		if(spriteMap == null){
			Log.logln(LogLevel.ERROR, "SpriteMapManager has not been initialized! Must run SpriteMapManager.init() before deleting items!");
			return;
		}
		
		if(!spriteMap.containsKey(tag)){
			Log.logln(LogLevel.ERROR, "SpriteMap '" + tag + "' is not registered in SpriteMapManager");
			return;
		} 
		
		else {
			spriteMap.remove(tag);
		}
	}
	
}
