package loreEngine.core.graphics;

public abstract class Renderer {
	
	protected Camera camera;
	protected Shader shader;
	
	protected Renderer() {}
	
	public Renderer(Camera camera, Shader shader) {
		init(camera, shader);
	}
	
	public void init(Camera camera, Shader shader) {
		this.camera = camera;
		this.shader = shader;
	}

}
