package loreEngine.core.graphics;

import loreEngine.core.graphics.vertex.IndexBuffer;
import loreEngine.core.graphics.vertex.VertexBuffer;
import loreEngine.core.graphics.vertex.VertexArray;

public class Mesh {
	
	private VertexArray  vao;
	private IndexBuffer  ibo;
	private VertexBuffer vbo;
	private VertexBuffer tbo;
	
	private int vertexCount;
	
	public Mesh(float[] vertices, int[] indices, float[] texCoords) {
		
		this.vao = new VertexArray();
		this.vbo = new VertexBuffer(vertices, 3);
		this.ibo = new IndexBuffer(indices);
		this.tbo = new VertexBuffer(texCoords, 2);
		
		vao.attach(vbo);
		vao.attach(tbo);
		
		vertexCount = ibo.getSize();
	}
	
	public int getVertexCount() {
		return vertexCount;
	}

	public VertexArray getVAO() {
		return vao;
	}
	
	public VertexBuffer getVBO() {
		return vbo;
	}

	public IndexBuffer getIBO() {
		return ibo;
	}

	public VertexBuffer getTBO() {
		return tbo;
	}

	public static Mesh Plane() {
		
		float[] vertices = new float[]
		{
			    -0.5f,  0.5f, 0.0f,
			    -0.5f, -0.5f, 0.0f,
			     0.5f, -0.5f, 0.0f,
			     0.5f,  0.5f, 0.0f,
		};
		 
		int[] indices = new int[]
		{
			     0, 1, 3, 3, 1, 2,
		};
		
		float[] texCoords = new float[] 
		{
				-0.5f,  0.5f,
			    -0.5f, -0.5f,
			     0.5f, -0.5f,
			     0.5f,  0.5f
		};
		
		return new Mesh(vertices, indices, texCoords);
	}

}
