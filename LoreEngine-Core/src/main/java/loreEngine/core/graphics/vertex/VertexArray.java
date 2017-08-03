package loreEngine.core.graphics.vertex;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

	private int vaoID;

	private int index;

	public VertexArray() {
		vaoID = glGenVertexArrays();
	}
	
	public void attach(VertexBuffer vBuffer) {
		glBindVertexArray(vaoID);
		vBuffer.bind();
		
		index++;
		glVertexAttribPointer(index - 1, vBuffer.getVecSize(), GL_FLOAT, false, 0, 0);
		
		vBuffer.unbind();
		glBindVertexArray(0);	
	}
	
	public void bind() {
		glBindVertexArray(vaoID);
		for(int i = 0; i <= index; i++)
			glEnableVertexAttribArray(index);
	}
	
	public void unbind() {
		for(int i = 0; i <= index; i++)
			glDisableVertexAttribArray(index);
		glBindVertexArray(0);
	}
	
	public void delete() {
		glDeleteBuffers(vaoID);
	}
	
	public int getID() {
		return vaoID;
	}

	public int getCurrentIndex() {
		return index;
	}
	
}
