package loreEngine.addons.camera;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.math.Vector3f;

public class SimpleCamera extends Camera{
	
	public static final float DEFAULT_FOV = 90.0f;
	
	public enum Projection {
		ORTHOGRAPHIC,
		PERSPECTIVE
	}
	
	public SimpleCamera(float x, float y, float z) {
		this(new Vector3f(x, y, z), new Vector3f(0, 0, 0), Projection.PERSPECTIVE, DEFAULT_FOV);
	}
	
	public SimpleCamera(Vector3f pos) {
		this(pos, new Vector3f(0, 0, 0), Projection.PERSPECTIVE, DEFAULT_FOV);
	}

	public SimpleCamera(Vector3f pos, Vector3f rot) {
		this(pos, rot, Projection.PERSPECTIVE, DEFAULT_FOV);
	}
	
	public SimpleCamera(Vector3f pos, Vector3f rot, Projection projection) {
		this(pos, rot, projection, DEFAULT_FOV);
	}
	
	public SimpleCamera(Vector3f pos, Vector3f rot, Projection projection, float fov) {
		super(pos, rot, (short)projection.ordinal(), 16, 9, fov);
	}

}
