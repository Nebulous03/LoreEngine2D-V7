package loreEngine.core.graphics.renderer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryUtil;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.shader.Shader;
import loreEngine.core.graphics.texture.Texture;

public abstract class BatchRenderer extends Renderer{

	protected FloatBuffer vertexBuffer;
	protected Texture batchTexture;
	
	protected int batchVAO;
	protected int batchVBO;
	protected int batchIBO;
	
	protected int indexCount;
	
	protected boolean drawing;
	
	protected BatchRenderer() {}
	
	public BatchRenderer(Texture batchTexture, Camera camera, Shader shader) {
		super(camera, shader);
		this.batchTexture = batchTexture;
		this.drawing = false;
	}
	
	public void createBatchData(int maxBufferSize, int vertexDataSize, int maxIndicesSize) {
		
		batchVAO = glGenVertexArrays();
		glBindVertexArray(batchVAO);
		
		batchVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, batchVBO);
		glBufferData(GL_ARRAY_BUFFER, maxBufferSize, GL_DYNAMIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, vertexDataSize, 0);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, vertexDataSize, 3 * Float.BYTES);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, vertexDataSize, (3 + 4) * Float.BYTES);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		int[] indices = new int[maxIndicesSize];
		
		int offset = 0;
		for(int i = 0; i < maxIndicesSize; i += 6) {
			
			indices[i + 0] = offset + 0;
			indices[i + 1] = offset + 1;
			indices[i + 2] = offset + 2;
			
			indices[i + 3] = offset + 2;
			indices[i + 4] = offset + 3;
			indices[i + 5] = offset + 0;
			
			offset += 4;
		}
		
		IntBuffer indexBuffer = MemoryUtil.memAllocInt(maxIndicesSize);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		batchIBO = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, batchIBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
	}
	
	public int getBatchVAO() {
		return batchVAO;
	}

	public int getBatchVBO() {
		return batchVBO;
	}
	
	public int getBatchIBO() {
		return batchIBO;
	}

	public boolean isDrawing() {
		return drawing;
	}
	
}
