package loreEngine.core.logic;

import java.util.HashMap;

import loreEngine.core.Game;
import loreEngine.core.graphics.layer.Scene;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class SceneManager {

	protected static HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	
	protected static Scene activeScene = null;
	
	public static void register(Scene scene) {
		if(scene.TAG == "NONE") scene.TAG = "unnamed_scene_" + scenes.size(); 
		scenes.put(scene.TAG, scene);
	}
	
	public static void load(String tag, Game game) {
		if(scenes.containsKey(tag)) {
			if(activeScene != null) unload(game);
			activeScene = scenes.get(tag);
			activeScene.create(game);
			activeScene.onLoad(game);
			activeScene = scenes.get(tag);
			Log.logln(LogLevel.INFO, "Successfully Loaded Scene: '" + activeScene.getTag() + "'");
		} else {
			Log.logln(LogLevel.ERROR, "Error loading scene! '" + tag + "' is not registered, or does not exist.");
		}
		
	}
	
	public static void unload(Game game) {
		if(activeScene != null) {
			activeScene.unload();
			activeScene.dispose(game);
			Log.logln(LogLevel.INFO, "Successfully Unloaded Scene: '" + activeScene.getTag() + "'");
			activeScene = null;
		}
	}
	
	public static Scene getActiveScene() {
		return activeScene;
	}
}
