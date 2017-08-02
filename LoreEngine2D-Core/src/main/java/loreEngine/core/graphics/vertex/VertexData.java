package loreEngine.core.graphics.vertex;

import loreEngine.math.Vector2f;
import loreEngine.math.Vector3f;
import loreEngine.math.Vector4f;

public class VertexData {
	
	public Vector3f vertex;
	public Vector4f color;
	public Vector2f texCoord;
	
	public VertexData(Vector3f vertex, Vector4f color, Vector2f texCoord) {
		this.vertex = vertex;
		this.color = color;
		this.texCoord = texCoord;
	}
	
	public int getSize() {
		return Vector3f.getSize() + Vector4f.getSize() + Vector2f.getSize();
	}

}
