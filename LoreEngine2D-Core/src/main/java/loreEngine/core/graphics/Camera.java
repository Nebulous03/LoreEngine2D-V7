package loreEngine.core.graphics;

import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Camera {

	public static final short CAMERA_ORTOGRAPHIC = 0;
	public static final short CAMERA_PERSPECTIVE = 1;

	private static float near = 0.001f;
	private static float far = 1000.0f;
	
	private Matrix4f projection;
	private short projectionType;
	
	private float width;
	private float height;
	
	private float fov;
	
	private Vector3f pos;
	private Vector3f rot;
	
	private Matrix4f translation;
	private Matrix4f rotation;
	
	public Camera(Vector3f pos, Vector3f rot, short projection, float width, float height, float fov) {
		this.pos = pos.mul(-1.0f);
		this.rot = rot;
		this.projectionType = projection;
		this.width = width;
		this.height = height;
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
		//updateVectors();
		return this;
	}
	
	public Matrix4f getViewMatrix() {
		return (Matrix4f.Identity()).mul(rotation).mul(translation);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
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
	
	public Camera resize(float width, float height) {
		this.width = width;
		this.height = height;
		setProjection(projectionType);
		return this;
	}
	
	public Matrix4f getProjectionMatrix() {
		return projection;
	}
	
	public short getProjectionType() {
		return projectionType;
	}

	public Camera setProjection(short projectionType) {
		this.projectionType = projectionType;
		switch (projectionType)
		{
		case CAMERA_ORTOGRAPHIC:
			this.projection = Matrix4f.Orthographic(0, width, height, 0, near, -far);
			break;
		case CAMERA_PERSPECTIVE:
			this.projection = Matrix4f.Perspective(fov, width / height, near, far);
			break;
		default:
			this.projection = Matrix4f.Orthographic(0, width, height, 0, near, -far);
			break;
		}
		return this;
	}
	
}
