package loreEngine.core.graphics;

import loreEngine.core.graphics.vertex.IndexBuffer;
import loreEngine.core.graphics.vertex.VertexBuffer;
import loreEngine.core.graphics.vertex.VertexArray;

public class Mesh {
	
	private VertexArray  vao;
	private IndexBuffer  ibo;
	private VertexBuffer cbo;
	private VertexBuffer vbo;
	private VertexBuffer tbo;
	
	public float[] vertices;
	public float[] colors;
	public float[] texCoords;
	public int[]   indices;
	
	private int vertexCount;
	
	public Mesh(float[] vertices, float[] colors, int[] indices, float[] texCoords) {
		
		this.vao = new VertexArray();
		this.vbo = new VertexBuffer(vertices, 3);
		this.cbo = new VertexBuffer(colors, 4);
		this.tbo = new VertexBuffer(texCoords, 2);
		this.ibo = new IndexBuffer(indices);
		
		this.vertices = vertices;
		this.colors = colors;
		this.texCoords = texCoords;
		this.indices = indices;
		
		vao.attach(vbo);
		vao.attach(cbo);
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
	
	public VertexBuffer getCBO() {
		return tbo;
	}
	
	public VertexBuffer getTBO() {
		return tbo;
	}

	public IndexBuffer getIBO() {
		return ibo;
	}

	public static Mesh Plane() {
		
		float[] vertices = new float[]
		{
			    -0.5f,  0.5f, 0.0f,
			    -0.5f, -0.5f, 0.0f,
			     0.5f, -0.5f, 0.0f,
			     0.5f,  0.5f, 0.0f,
		};
		
		float[] colors = new float[]
		{
				1.0f,  0.0f, 0.0f, 1.0f,
				1.0f,  0.0f, 1.0f, 1.0f,
				1.0f,  1.0f, 0.0f, 1.0f,
				0.0f,  1.0f, 1.0f, 1.0f
		};
		
		int[] indices = new int[]
		{
			     0, 1, 3, 3, 1, 2,
		};
		
		float[] texCoords = new float[] 
		{
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
		
		return new Mesh(vertices, colors, indices, texCoords);
	}

}
