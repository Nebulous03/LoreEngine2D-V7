package loreEngine.addons.tileMap;

public class TileMesh {
	
	public float[] vertices;
	public float[] colors;
	public float[] texCoords;
	
	public TileMesh() {
		vertices = new float[]
		{
			    -0.5f,  0.5f, 0.0f,
			    -0.5f, -0.5f, 0.0f,
			     0.5f, -0.5f, 0.0f,
			     0.5f,  0.5f, 0.0f,
		};
		
		colors = new float[]
		{
				1.0f,  1.0f, 1.0f, 1.0f,
				1.0f,  1.0f, 1.0f, 1.0f,
				1.0f,  1.0f, 1.0f, 1.0f,
				1.0f,  1.0f, 1.0f, 1.0f
		};
		
		texCoords = new float[] 
		{
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
	}

}