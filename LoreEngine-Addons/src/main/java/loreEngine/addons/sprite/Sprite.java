package loreEngine.addons.sprite;

import java.awt.Color;

import loreEngine.core.graphics.renderer.Renderable;
import loreEngine.core.graphics.texture.Texture;
import loreEngine.core.graphics.vertex.Mesh;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Sprite extends Renderable {

	public Sprite(Texture texture, int x, int y) {
		super(Mesh.Plane(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), texture, Color.WHITE);
	}
	
	public Sprite move(float deltaX, float deltaY) {
		super.translation.mul(Matrix4f.Translation(new Vector3f(deltaX, deltaY, 0)));	//TODO: Speed this up!!!
		return this;
	}
	
	public Sprite rotate(int rotX) {
		super.rotation.mul(Matrix4f.Rotation(new Vector3f(rotX, 0, 0)));	//TODO: Speed this up!!!
		return this;
	}
	
	public Sprite scale(int scale) {
		super.scale.mul(Matrix4f.Scale(new Vector3f(scale, scale, 0)));	//TODO: Speed this up!!!
		return this;
	}
	
}
