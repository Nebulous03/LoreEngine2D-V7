package loreEngine.math;

public class Vector2f {
	
	private static final int SIZE = 2 * Float.BYTES;
	
	public float x;
	public float y;
	
	public Vector2f(float x, float y, float z) {
		this.x = x;
		this.y = y;
	}
	
	/* ADD */
	
	public Vector2f add(Vector2f other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2f add(Vector2f other, Vector2f dest) {
		dest.x += other.x;
		dest.y += other.y;
		return this;
	}
	
	public Vector2f add(float other) {
		this.x += other;
		this.y += other;
		return this;
	}
	
	public Vector2f add(float other, Vector2f dest) {
		dest.x += other;
		dest.y += other;
		return this;
	}
	
	/* SUBTRACT */
	
	public Vector2f sub(Vector2f other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2f sub(Vector2f other, Vector2f dest) {
		dest.x -= other.x;
		dest.y -= other.y;
		return this;
	}
	
	public Vector2f sub(float other) {
		this.x -= other;
		this.y -= other;
		return this;
	}
	
	public Vector2f sub(float other, Vector2f dest) {
		dest.x -= other;
		dest.y -= other;
		return this;
	}
	
	/* MULTIPLY */
	
	public Vector2f mul(Vector2f other) {
		this.x *= other.x;
		this.y *= other.y;
		return this;
	}
	
	public Vector2f mul(Vector2f other, Vector2f dest) {
		dest.x *= other.x;
		dest.y *= other.y;
		return this;
	}
	
	public Vector2f mul(float other) {
		this.x *= other;
		this.y *= other;
		return this;
	}
	
	public Vector2f mul(float other, Vector2f dest) {
		dest.x *= other;
		dest.y *= other;
		return this;
	}
	
	/* DIVIDE */
	
	public Vector2f div(Vector2f other) {
		this.x /= other.x;
		this.y /= other.y;
		return this;
	}
	
	public Vector2f div(Vector2f other, Vector2f dest) {
		dest.x /= other.x;
		dest.y /= other.y;
		return this;
	}
	
	public Vector2f div(float other) {
		this.x /= other;
		this.y /= other;
		return this;
	}
	
	public Vector2f div(float other, Vector2f dest) {
		dest.x *= other;
		dest.y *= other;
		return this;
	}

	/* OTHER */
	
	public static int getSize() {
		return SIZE;
	}
	
}
