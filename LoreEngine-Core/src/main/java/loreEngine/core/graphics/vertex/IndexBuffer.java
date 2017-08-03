package loreEngine.core.graphics.vertex;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.IntBuffer;

import org.lwjgl.system.MemoryUtil;

public class IndexBuffer {
	
	private IntBuffer data;
	
	private int bufferID;
	private int size;	
	
	public IndexBuffer(int[] data) {
		
		this.size = data.length;
		this.data = MemoryUtil.memAllocInt(data.length);
		this.data.put(data).flip();
		
		bufferID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, this.data, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
	}
	
	public void delete() {
		glDeleteBuffers(bufferID);
	}

	public void bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferID);
	}
	
	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public IntBuffer getData() {
		return data;
	}

	public int getBufferID() {
		return bufferID;
	}

	public int getSize() {
		return size;
	}

}
