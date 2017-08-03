package loreEngine.math;

public class Vector3f {
	
	private static final int SIZE = 3 * Float.BYTES;
	
	public float x;
	public float y;
	public float z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/* ADD */
	
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
	
	public Vector3f add(float other) {
		this.x += other;
		this.y += other;
		this.z += other;
		return this;
	}
	
	public Vector3f add(float other, Vector3f dest) {
		dest.x += other;
		dest.y += other;
		dest.z += other;
		return this;
	}
	
	/* SUBTRACT */
	
	public Vector3f sub(Vector3f other) {
		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
		return this;
	}
	
	public Vector3f sub(Vector3f other, Vector3f dest) {
		dest.x -= other.x;
		dest.y -= other.y;
		dest.z -= other.z;
		return this;
	}
	
	public Vector3f sub(float other) {
		this.x -= other;
		this.y -= other;
		this.z -= other;
		return this;
	}
	
	public Vector3f sub(float other, Vector3f dest) {
		dest.x -= other;
		dest.y -= other;
		dest.z -= other;
		return this;
	}
	
	/* MULTIPLY */
	
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
	
	public Vector3f mul(float other) {
		this.x *= other;
		this.y *= other;
		this.z *= other;
		return this;
	}
	
	public Vector3f mul(float other, Vector3f dest) {
		dest.x *= other;
		dest.y *= other;
		dest.z *= other;
		return this;
	}
	
	/* DIVIDE */
	
	public Vector3f div(Vector3f other) {
		this.x /= other.x;
		this.y /= other.y;
		this.z /= other.z;
		return this;
	}
	
	public Vector3f div(Vector3f other, Vector3f dest) {
		dest.x /= other.x;
		dest.y /= other.y;
		dest.z /= other.z;
		return this;
	}
	
	public Vector3f div(float other) {
		this.x /= other;
		this.y /= other;
		this.z /= other;
		return this;
	}
	
	public Vector3f div(float other, Vector3f dest) {
		dest.x *= other;
		dest.y *= other;
		dest.z *= other;
		return this;
	}
	
	/* OTHER */
	
	public static int getSize() {
		return SIZE;
	}

}
