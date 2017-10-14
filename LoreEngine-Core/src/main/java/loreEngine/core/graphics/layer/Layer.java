package loreEngine.core.graphics.layer;

import java.util.ArrayList;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.renderer.Renderable;
import loreEngine.core.graphics.renderer.Renderer;
import loreEngine.core.graphics.shader.Shader;

public abstract class Layer {
	
	public String TAG = "NONE";
	private boolean visable = true;
	
	private Renderer renderer;
	private Camera camera;
	private Shader shader;
	
	private ArrayList<Renderable> objects;
	
	public Layer(Renderer renderer, Camera camera, Shader shader) {
		this.renderer = renderer;
		this.camera = camera;
		this.shader = shader;
		objects = new ArrayList<Renderable>();
	}
	
	public Layer add(Renderable renderable) {
		objects.add(renderable);
		return this;
	}
	
	public Layer remove(Renderable renderable) {
		objects.remove(renderable);
		return this;
	}
	
	public void hide() {
		visable = false;
	}
	
	public void show() {
		visable = true;
	}
	
	public abstract void create();

	public abstract void dispose();
	
	public void render() {
		if(visable) {
			for(int i = 0; i < objects.size(); i++) {
				renderer.render(objects.get(i), camera, shader);
				System.out.println(TAG + " render");
			}
		}
	}
	
	public boolean isVisable() {
		return visable;
	}

}
