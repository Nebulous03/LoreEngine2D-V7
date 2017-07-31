package loreEngine.math;

public class Vector3f {
	
	public float x;
	public float y;
	public float z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f add(Vector3f other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
		return this;
	}
	
	public Vector3f add(Vector3f other, Vector3f dest) {
		dest.x += other.x;
		dest.y += other.y;
		dest.z += other.z;
		return this;
	}
	
	public Vector3f mul(Vector3f other) {
		this.x *= other.x;
		this.y *= other.y;
		this.z *= other.z;
		return this;
	}
	
	public Vector3f mul(Vector3f other, Vector3f dest) {
		dest.x *= other.x;
		dest.y *= other.y;
		dest.z *= other.z;
		return this;
	}

}
