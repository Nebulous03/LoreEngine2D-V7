package loreEngine.core.graphics.renderer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_WRITE_ONLY;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.glMapBuffer;
import static org.lwjgl.opengl.GL15.glUnmapBuffer;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

import lore.math.Matrix4f;
import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.shader.Shader;
import loreEngine.core.graphics.texture.Texture;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public abstract class BatchRenderer extends Renderer{

	protected FloatBuffer vertexBuffer;
	protected Texture batchTexture;
	
	protected int batchVAO;
	protected int batchVBO;
	protected int batchIBO;
	
	protected int indexCount;
	
	protected boolean drawing;
	
	protected int vertexDataSize;
	
	protected BatchRenderer() {}
	
	public BatchRenderer(Texture batchTexture, Camera camera, Shader shader) {
		super(camera, shader);
		this.batchTexture = batchTexture;
		this.drawing = false;
	}
	
	public void createBatchData(int maxBufferSize, int vertexDataSize, int maxIndicesSize) {
		
		this.vertexDataSize = vertexDataSize;
		
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

	public void addToVBO(float[] vertices, float[] colors, float offsetX, float offsetY, float offsetZ, float texPosX, float texPosY, float texWidth, float texHeight) {
		
		if(vertexBuffer.remaining() <= vertexDataSize) {
			Log.logln(LogLevel.INFO, "Batch Renderer buffer overflow; flushing and starting new batch.");
			flush();
		}
		
		vertexBuffer.put(vertices[0] + offsetX).put(vertices[1] + offsetY).put(vertices[2] + offsetZ);
		vertexBuffer.put(colors[0]).put(colors[1]).put(colors[2]).put(colors[3]);
		vertexBuffer.put(texPosX).put(texPosY);
		
		vertexBuffer.put(vertices[3] + offsetX).put(vertices[4] + offsetY).put(vertices[5] + offsetZ);
		vertexBuffer.put(colors[4]).put(colors[5]).put(colors[6]).put(colors[7]);
		vertexBuffer.put(texPosX).put(texPosY + texHeight);
		
		vertexBuffer.put(vertices[6] + offsetX).put(vertices[7] + offsetY).put(vertices[8] + offsetZ);
		vertexBuffer.put(colors[8]).put(colors[9]).put(colors[10]).put(colors[11]);
		vertexBuffer.put(texPosX + texWidth).put(texPosY + texHeight);
		
		vertexBuffer.put(vertices[9] + offsetX).put(vertices[10] + offsetY).put(vertices[11] + offsetZ);
		vertexBuffer.put(colors[12]).put(colors[13]).put(colors[14]).put(colors[15]);
		vertexBuffer.put(texPosX + texWidth).put(texPosY);
		
		indexCount += 6;
		
	}
	
	public void begin() {
		
		if(drawing){
			Log.logln(LogLevel.WARNING, "Attempted to begin batch, but the batch is already drawing.");
			return;
		}
		
		glBindVertexArray(batchVAO);
		glBindBuffer(GL_ARRAY_BUFFER, batchVBO);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, batchIBO);
		vertexBuffer = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY).order(ByteOrder.nativeOrder()).asFloatBuffer();
		drawing = true;
	}
	
	public void end() {
		
		if(!drawing){
			Log.logln(LogLevel.WARNING, "Attempted to end batch, but the batch is not drawing.");
			return;
		}
		
		glUnmapBuffer(GL_ARRAY_BUFFER);
		flush();
		vertexBuffer.clear();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		drawing = false;
		
	}
	
	public void flush() {
		
		shader.bind();
		
		if(batchTexture != null) batchTexture.bind(shader, "vTexture");
		
		shader.setUniform("projection", camera.getProjectionMatrix());
		shader.setUniform("view", camera.getViewMatrix());
		shader.setUniform("transform", Matrix4f.Identity());
		
		glDrawElements(GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);
		
		if(batchTexture != null) batchTexture.unbind();
		
		shader.unbind();
		
		vertexBuffer.clear();
		indexCount = 0;
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
	
	@Override
	public void delete() {
		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(batchVAO);
		GL15.glDeleteBuffers(batchVBO);
		GL15.glDeleteBuffers(batchIBO);
	}
	
}
