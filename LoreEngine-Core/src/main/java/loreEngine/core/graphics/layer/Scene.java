package loreEngine.core.graphics.layer;

import java.util.ArrayList;
import java.util.HashMap;

import loreEngine.core.Game;

public abstract class Scene {
	
	public String TAG = "NONE";
	private int layerTagCount = 0;
	protected HashMap<String, Layer> layersHash;
	protected ArrayList<Layer> layers;
	
	public Scene(String tag) {
		this.layers = new ArrayList<Layer>();
		this.layersHash = new HashMap<String, Layer>();
		this.TAG = tag;
	}
	
	public void unload() {
		for(int i = 0; i < layers.size(); i++)
			layers.get(i).dispose();
		layersHash.clear();
	}
	
	public Scene addLayer(Layer layer) {
		if(layer.TAG == "NONE") layer.TAG = "unnamed_layer_" + layerTagCount;
		layersHash.put(layer.TAG, layer);
		layers.add(layer);
		layer.create();
		layerTagCount++;
		return this;
	}
	
	public Scene removeLayer(String tag) {
		layersHash.get(tag).dispose();
		layersHash.remove(tag);
		layers.remove(layersHash.get(tag));
		return this;
	}
	
	public abstract void create(Game game);
	
	public abstract void onLoad(Game game);
	
	public abstract void onUnload(Game game);
	
	public abstract void dispose(Game game);
	
	public void render() {
		for(int i = 0; i < layers.size(); i++) {
			layers.get(i).render();
		}
	}

	public String getTag() {
		return TAG;
	}
	
}
