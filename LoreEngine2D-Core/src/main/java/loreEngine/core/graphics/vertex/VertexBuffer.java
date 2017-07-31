package loreEngine.core.graphics.vertex;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class VertexBuffer {
	
	private FloatBuffer data;
	
	private int bufferID;
	private int vecSize;
	private int size;
	
	public VertexBuffer(float[] data, int vecSize) {
		
		this.size = data.length;
		this.vecSize = vecSize;
		this.data = MemoryUtil.memAllocFloat(data.length);
		this.data.put(data).flip();
		
		bufferID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, bufferID);
		glBufferData(GL_ARRAY_BUFFER, this.data, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}
	
	public void delete() {
		glDeleteBuffers(bufferID);
	}

	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, bufferID);
	}
	
	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public FloatBuffer getData() {
		return data;
	}

	public int getBufferID() {
		return bufferID;
	}

	public int getVecSize() {
		return vecSize;
	}

	public int getSize() {
		return size;
	}
	
}
