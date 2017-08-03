package loreEngine.addons.camera;

import loreEngine.core.graphics.Window;
import loreEngine.core.graphics.camera.Camera;
import loreEngine.math.Vector3f;

public class SimpleCamera extends Camera{
	
	public static final float DEFAULT_FOV = 90.0f;
	
	public enum Projection {
		ORTHOGRAPHIC,
		PERSPECTIVE
	}
	
	public SimpleCamera(Window window, float x, float y, float z) {
		this(window, new Vector3f(x, y, z), new Vector3f(0, 0, 0), Projection.PERSPECTIVE, DEFAULT_FOV);
	}
	
	public SimpleCamera(Window window, Vector3f pos) {
		this(window, pos, new Vector3f(0, 0, 0), Projection.PERSPECTIVE, DEFAULT_FOV);
	}

	public SimpleCamera(Window window, Vector3f pos, Vector3f rot) {
		this(window, pos, rot, Projection.PERSPECTIVE, DEFAULT_FOV);
	}
	
	public SimpleCamera(Window window, Vector3f pos, Vector3f rot, Projection projection) {
		this(window, pos, rot, projection, DEFAULT_FOV);
	}
	
	public SimpleCamera(Window window, Vector3f pos, Vector3f rot, Projection projection, float fov) {
		super(window, pos, rot, (short)projection.ordinal(), fov);
	}

}
