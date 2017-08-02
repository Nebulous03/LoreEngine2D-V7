package loreEngine.core.graphics.renderers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Stack;

import org.lwjgl.system.MemoryUtil;

import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.Mesh;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Renderer;
import loreEngine.core.graphics.Shader;
import loreEngine.core.graphics.Texture;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class BatchRenderer extends Renderer {
	
	private static final int MAX_RENDERABLES  = 65535;
	private static final int VERTEX_DATA_SIZE = (3 + 4 + 2) * Float.BYTES;
	private static final int MAX_INDICES_SIZE = MAX_RENDERABLES * 6;
	private static final int MAX_BUFFER_SIZE  = MAX_INDICES_SIZE * VERTEX_DATA_SIZE;
	
	private Stack<Matrix4f> transformStack;
	private Matrix4f activeTransform;

	private FloatBuffer vertexBuffer;
	private Texture batchTexture;
	
	private int batchVAO;
	private int batchVBO;
	private int batchIBO;
	
	private int indexCount;
	
	private boolean drawing;
	
	public BatchRenderer(Texture batchTexture, Camera camera, Shader shader) {
		super(camera, shader);
		this.transformStack = new Stack<Matrix4f>();
		this.transformStack.push(Matrix4f.Identity());
		this.activeTransform = transformStack.peek();
		this.batchTexture = batchTexture;
		this.drawing = false;
		createBatchData();
	}
	
	public void createBatchData() {
		
		batchVAO = glGenVertexArrays();
		glBindVertexArray(batchVAO);
		
		batchVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, batchVBO);
		glBufferData(GL_ARRAY_BUFFER, MAX_BUFFER_SIZE, GL_DYNAMIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, VERTEX_DATA_SIZE, 0);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, VERTEX_DATA_SIZE, 3 * Float.BYTES);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, VERTEX_DATA_SIZE, (3 + 4) * Float.BYTES);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		int[] indices = new int[MAX_INDICES_SIZE];
		
		int offset = 0;
		for(int i = 0; i < MAX_INDICES_SIZE; i += 6) {
			
			indices[i + 0] = offset + 0;
			indices[i + 1] = offset + 1;
			indices[i + 2] = offset + 2;
			
			indices[i + 3] = offset + 2;
			indices[i + 4] = offset + 3;
			indices[i + 5] = offset + 0;
			
			offset += 4;
		}
		
		IntBuffer indexBuffer = MemoryUtil.memAllocInt(MAX_INDICES_SIZE);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		batchIBO = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, batchIBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
	}
	
	public void begin() {
		
		if(drawing){
			Log.logln(LogLevel.WARNING, "Attempted to begin batch, but the batch is already drawing.");
			return;
		}
		
		glBindVertexArray(batchVAO);
		glBindBuffer(GL_ARRAY_BUFFER, batchVBO);
		vertexBuffer = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY).order(ByteOrder.nativeOrder()).asFloatBuffer();
		drawing = true;
	}

	Mesh mesh = null;
	Vector3f trans = null;
	
	public void push(Renderable renderable) {	//TODO: Make dynamically
		
		if(vertexBuffer.remaining() == 0) {
			Log.logln(LogLevel.DEBUG, "Batch Renderer buffer overflow; flushing and starting new batch.");
			flush();
		}
		
		mesh = renderable.getMesh();
		trans = activeTransform.getPosVec3f();
		
		vertexBuffer.put(mesh.vertices[0] + trans.x).put(mesh.vertices[1] + trans.y).put(mesh.vertices[2] + trans.z);
		vertexBuffer.put(mesh.colors[0]).put(mesh.colors[1]).put(mesh.colors[2]).put(mesh.colors[3]);
		vertexBuffer.put(mesh.texCoords[0]).put(mesh.texCoords[1]);
		
		vertexBuffer.put(mesh.vertices[3] + trans.x).put(mesh.vertices[4] + trans.y).put(mesh.vertices[5] + trans.z);
		vertexBuffer.put(mesh.colors[4]).put(mesh.colors[5]).put(mesh.colors[6]).put(mesh.colors[7]);
		vertexBuffer.put(mesh.texCoords[2]).put(mesh.texCoords[3]);
		
		vertexBuffer.put(mesh.vertices[6] + trans.x).put(mesh.vertices[7] + trans.y).put(mesh.vertices[8] + trans.z);
		vertexBuffer.put(mesh.colors[8]).put(mesh.colors[9]).put(mesh.colors[10]).put(mesh.colors[11]);
		vertexBuffer.put(mesh.texCoords[4]).put(mesh.texCoords[5]);
		
		vertexBuffer.put(mesh.vertices[9] + trans.x).put(mesh.vertices[10] + trans.y).put(mesh.vertices[11] + trans.z);
		vertexBuffer.put(mesh.colors[12]).put(mesh.colors[13]).put(mesh.colors[14]).put(mesh.colors[15]);
		vertexBuffer.put(mesh.texCoords[6]).put(mesh.texCoords[7]);
		
		indexCount += 6;
	}
	
	public void pushTransform(Matrix4f transform) {
		Matrix4f newTransform = Matrix4f.Identity().mul(transformStack.peek()).mul(transform);
		activeTransform = newTransform;
		transformStack.push(newTransform);
	}
	
	public void popTransform() {
		if(transformStack.size() > 1) transformStack.pop();
		activeTransform = transformStack.peek();
	}
	
	public void end() {
		
		if(!drawing){
			Log.logln(LogLevel.WARNING, "Attempted to end batch, but the batch is not drawing.");
			return;
		}
		
		glUnmapBuffer(GL_ARRAY_BUFFER);
		flush();
		vertexBuffer.clear();
		glBindVertexArray(0);
		drawing = false;
		
		transformStack.clear();
		transformStack.push(Matrix4f.Identity());
	}
	
	public void flush() {
		
		shader.bind();
		
		batchTexture.bind(shader, "vTexture");
		
		shader.setUniform("projection", camera.getProjectionMatrix());
		shader.setUniform("view", camera.getViewMatrix());
		shader.setUniform("transform", Matrix4f.Identity());
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, batchIBO);
		glDrawElements(GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		batchTexture.unbind();
		
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

	public boolean isDrawing() {
		return drawing;
	}

}
