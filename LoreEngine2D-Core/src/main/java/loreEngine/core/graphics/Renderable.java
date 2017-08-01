package loreEngine.core.graphics;

import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Renderable {
	
	private Mesh mesh;
	
	private Matrix4f translation;
	private Matrix4f rotation;
	private Matrix4f scale;
	
	public Renderable(Mesh mesh, Vector3f pos, Vector3f rot, Vector3f scale) {
		this.mesh = mesh;
		this.translation = Matrix4f.Translation(pos);
		this.rotation = Matrix4f.Rotation(rot);
		this.scale = Matrix4f.Scale(scale);
	}
	
	public Matrix4f getTransformMatrix() {
		return Matrix4f.Identity().mul(rotation).mul(scale).mul(translation);
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Matrix4f getTranslation() {
		return translation;
	}

	public Matrix4f getRotation() {
		return rotation;
	}

	public Matrix4f getScale() {
		return scale;
	}

}
