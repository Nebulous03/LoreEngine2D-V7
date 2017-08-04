package loreEngine.addons.managers;

import java.util.HashMap;

import loreEngine.core.graphics.shader.Shader;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class ShaderManager {
	
	public static HashMap<String, Shader> shaderMap;

	public static void init() {
		shaderMap = new HashMap<String, Shader>();
	}

	public static void register(String tag, Shader renderer) {
		if(shaderMap == null)
			Log.logln(LogLevel.ERROR, "ShaderManager has not been initialized! Must run ShaderManager.init() before registering items!");
		else 
			shaderMap.put(tag, renderer);
	}

	@SuppressWarnings("unchecked")
	public static <R> R get(String tag) {
		
		if(shaderMap == null) {
			Log.logln(LogLevel.ERROR, "ShaderManager has not been initialized! Must run ShaderManager.init() before getting items!");
			return null;
		}
		
		if(!shaderMap.containsKey(tag)) {
			Log.logln(LogLevel.ERROR, "Shader '" + tag + "' is not registered in ShaderManager");
			return null;
		}
		
		return (R)shaderMap.get(tag);
	}
	
	public static void delete(String tag) {
		
		if(shaderMap == null){
			Log.logln(LogLevel.ERROR, "ShaderManager has not been initialized! Must run ShaderManager.init() before deleting items!");
			return;
		}
		
		if(!shaderMap.containsKey(tag)){
			Log.logln(LogLevel.ERROR, "Shader '" + tag + "' is not registered in ShaderManager");
			return;
		} 
		
		else {
			shaderMap.get(tag).delete();
			shaderMap.remove(tag);
		}
	}
	
}
