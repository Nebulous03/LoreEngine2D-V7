package loreEngine.math;

public class Vector4f {
	
	private static final int SIZE = 4 * Float.BYTES;
	
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/* ADD */
	
	public Vector4f add(Vector4f other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
		this.w += other.w;
		return this;
	}
	
	public Vector4f add(Vector4f other, Vector4f dest) {
		dest.x += other.x;
		dest.y += other.y;
		dest.z += other.z;
		this.w += other.w;
		return this;
	}
	
	public Vector4f add(float other) {
		this.x += other;
		this.y += other;
		this.z += other;
		this.w += other;
		return this;
	}
	
	public Vector4f add(float other, Vector4f dest) {
		dest.x += other;
		dest.y += other;
		dest.z += other;
		this.w += other;
		return this;
	}
	
	/* SUBTRACT */
	
	public Vector4f sub(Vector4f other) {
		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
		this.w -= other.w;
		return this;
	}
	
	public Vector4f sub(Vector4f other, Vector4f dest) {
		dest.x -= other.x;
		dest.y -= other.y;
		dest.z -= other.z;
		this.w -= other.w;
		return this;
	}
	
	public Vector4f sub(float other) {
		this.x -= other;
		this.y -= other;
		this.z -= other;
		this.w -= other;
		return this;
	}
	
	public Vector4f sub(float other, Vector4f dest) {
		dest.x -= other;
		dest.y -= other;
		dest.z -= other;
		this.w -= other;
		return this;
	}
	
	/* MULTIPLY */
	
	public Vector4f mul(Vector4f other) {
		this.x *= other.x;
		this.y *= other.y;
		this.z *= other.z;
		this.w *= other.w;
		return this;
	}
	
	public Vector4f mul(Vector4f other, Vector4f dest) {
		dest.x *= other.x;
		dest.y *= other.y;
		dest.z *= other.z;
		this.w *= other.w;
		return this;
	}
	
	public Vector4f mul(float other) {
		this.x *= other;
		this.y *= other;
		this.z *= other;
		this.w *= other;
		return this;
	}
	
	public Vector4f mul(float other, Vector4f dest) {
		dest.x *= other;
		dest.y *= other;
		dest.z *= other;
		this.w *= other;
		return this;
	}
	
	/* DIVIDE */
	
	public Vector4f div(Vector4f other) {
		this.x /= other.x;
		this.y /= other.y;
		this.z /= other.z;
		this.z /= other.w;
		return this;
	}
	
	public Vector4f div(Vector4f other, Vector4f dest) {
		dest.x /= other.x;
		dest.y /= other.y;
		dest.z /= other.z;
		this.z /= other.w;
		return this;
	}
	
	public Vector4f div(float other) {
		this.x /= other;
		this.y /= other;
		this.z /= other;
		this.z /= other;
		return this;
	}
	
	public Vector4f div(float other, Vector4f dest) {
		dest.x *= other;
		dest.y *= other;
		dest.z *= other;
		this.z /= other;
		return this;
	}
	
	/* OTHER */
	
	public static int getSize() {
		return SIZE;
	}

}
