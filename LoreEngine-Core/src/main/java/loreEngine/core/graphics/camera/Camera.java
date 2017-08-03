package loreEngine.core.graphics.camera;

import loreEngine.core.graphics.Window;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Camera {

	public static final short CAMERA_ORTOGRAPHIC = 0;
	public static final short CAMERA_PERSPECTIVE = 1;

	private static float near = 0.0001f;
	private static float far  = 1000.0f;
	
	private short projectionType;
	
	private float fov;
	
	private Vector3f pos;
	private Vector3f rot;
	
	private Matrix4f translation;
	private Matrix4f rotation;
	
	private Window window;
	
	public Camera(Window window, Vector3f pos, Vector3f rot, short projection, float fov) {
		this.window = window;
		this.pos = pos.mul(-1.0f);
		this.rot = rot;
		this.projectionType = projection;
		this.fov = fov;
		this.setProjection(projection);
		this.translation = Matrix4f.Translation(pos);
		this.rotation = Matrix4f.Rotation(rot);
	}
	
	public Camera move(Vector3f direction, float speed) {
		Vector3f temp = direction; temp.mul(speed);
		pos.sub(temp);
		translation = Matrix4f.Translation(pos);
		return this;
	}
	
	public Camera rotate(Vector3f axis, float angle) {
		Vector3f temp = axis; temp.mul(angle);
		rot.add(temp);

		if (rot.x >= 360.0f) rot.x -= 360.0f;
		if (rot.x <= -360.0f) rot.x += 360.0f;

		if (rot.y >= 360.0f) rot.y -= 360.0f;
		if (rot.y <= -360.0f) rot.y += 360.0f;

		if (rot.z >= 360.0f) rot.z -= 360.0f;
		if (rot.z <= -360.0f) rot.z += 360.0f;

		rotation = Matrix4f.Rotation(rot);
		return this;
	}
	
	public Matrix4f getViewMatrix() {
		return (Matrix4f.Identity()).mul(rotation).mul(translation);
	}

	public Matrix4f getProjectionMatrix() {
		switch (projectionType)
		{
		case CAMERA_ORTOGRAPHIC:
			return Matrix4f.Orthographic(0, window.getWidth(), window.getHeight(), 0, near, -far);
		case CAMERA_PERSPECTIVE:
			return Matrix4f.Perspective(fov, (float)window.getWidth() / (float)window.getHeight(), near, far);
		default:
			return null;	
		}
	}
	
	public float getWidth() {
		return window.getWidth();
	}

	public float getHeight() {
		return window.getHeight();
	}
	
	public Vector3f getPosition() {
		return pos;
	}
	
	public Vector3f getRotation() {
		return rot;
	}
	
	public Camera setPosition(Vector3f pos) {
		this.pos = pos;
		this.translation = Matrix4f.Translation(pos);
		return this;
	}

	public Camera setRotation(Vector3f rot) {
		this.rot = rot;
		this.rotation = Matrix4f.Rotation(rot);
		return this;
	}

	public Matrix4f getTranslationMatrix() {
		return translation;
	}
	
	public Matrix4f getRotationMatrix() {
		return rotation;
	}
	
	public float getFov() {
		return fov;
	}

	public Camera setFov(float fov) {
		this.fov = fov;
		setProjection(projectionType);
		return this;
	}

	public short getProjectionType() {
		return projectionType;
	}

	public Camera setProjection(short projectionType) {
		this.projectionType = projectionType;
		return this;
	}
	
}
