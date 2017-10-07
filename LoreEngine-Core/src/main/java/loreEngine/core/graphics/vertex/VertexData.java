package loreEngine.core.graphics.vertex;

import lore.math.Vector2f;
import lore.math.Vector3f;
import lore.math.Vector4f;

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
		return Vector3f.SIZE + Vector4f.SIZE + Vector2f.SIZE;
	}

}
