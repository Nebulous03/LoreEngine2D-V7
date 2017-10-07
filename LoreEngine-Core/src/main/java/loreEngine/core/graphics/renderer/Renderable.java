package loreEngine.core.graphics.renderer;

import java.awt.Color;

import lore.math.Matrix4f;
import lore.math.Vector3f;
import loreEngine.core.graphics.texture.Texture;
import loreEngine.core.graphics.vertex.Mesh;

public class Renderable {
	
	protected Mesh mesh;
	protected Texture texture;
	protected Color color;
	
	protected Matrix4f translation;
	protected Matrix4f rotation;
	protected Matrix4f scale;
	
	protected Renderable() {}
	
	public Renderable(Mesh mesh, Vector3f pos, Vector3f rot, Vector3f scale, Texture texture, Color color) {
		this.mesh = mesh;
		this.translation = Matrix4f.Translation(pos);
		this.rotation = Matrix4f.Rotation(rot);
		this.scale = Matrix4f.Scale(scale);
		this.texture = texture;
		this.color = color;
	}
	
	public Matrix4f getTransformMatrix() {
		return Matrix4f.Identity().mul(rotation).mul(scale).mul(translation);
	}

	public Mesh getMesh() {
		return mesh;
	}
	
	public Texture getTexture() {
		return texture;
	}

	public Matrix4f getTranslation() {
		return translation;
	}
	
	public Renderable setTranslation(Matrix4f translation) {
		this.translation = translation;
		return this;
	}

	public Matrix4f getRotation() {
		return rotation;
	}
	
	public Renderable setRotation(Matrix4f rotation) {
		this.rotation = rotation;
		return this;
	}

	public Matrix4f getScale() {
		return scale;
	}
	
	public Renderable setScale(Matrix4f scale) {
		this.scale = scale;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
