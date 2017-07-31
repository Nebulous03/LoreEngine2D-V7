package loreEngine.core.graphics;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.system.MemoryUtil;

import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;

public class Shader {
	
	private int program;
	
	private String vertLocation;
	private String fragLocation;
	
	@SuppressWarnings("unused")
	private HashMap<String, Integer> uniforms;
	
	public Shader(String vertexShader, String fragmentShader) {
		this.vertLocation = vertexShader;
		this.fragLocation = fragmentShader;
		String vs = readShader(vertexShader);
		String fs = readShader(fragmentShader);
		program = createShader(vs, fs);
		uniforms = new HashMap<String, Integer>();
	}
	
	protected int createShader(String vertexShader, String fragmentShader) {
		
		int program = glCreateProgram();
		if(program == 0) System.err.println("ERROR! - Error creating shader program! [" + vertexShader + " | " + fragmentShader + "]");
		
		/* VERTEX */
		int vertShader = glCreateShader(GL_VERTEX_SHADER);
		if(vertShader == 0) System.err.println("ERROR! - Error creating shader! [" + vertLocation + "]");
		glShaderSource(vertShader, vertexShader);
		glCompileShader(vertShader);
		
		if(glGetShaderi(vertShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("ERROR! - Failed to compile vertex shader! [" + vertLocation + "]");
			System.err.println("Reason: " + glGetShaderInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
			System.exit(1);
		}
		
		glAttachShader(program, vertShader);
		
		/* FRAGMENT */
		int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
		if(fragShader == 0) System.err.println("ERROR! - Error creating shader! [" + fragLocation + "]");
		glShaderSource(fragShader, fragmentShader);
		glCompileShader(fragShader);
		
		if(glGetShaderi(fragShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("ERROR! - Failed to compile fragment shader! [" + fragLocation + "]");
			System.err.println("Reason: " + glGetShaderInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
			System.exit(1);
		}
		
		glAttachShader(program, fragShader);
		
		/* LINK */
		glLinkProgram(program);
		
		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
		    System.err.println("ERROR! - Failed to link shader! [" + vertexShader + " | " + fragmentShader + "]");
		    System.err.println(glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
		    System.exit(1);
		}
		
		glDetachShader(program, vertShader);
		glDetachShader(program, fragShader);
		
		/* VALIDATE */
		glValidateProgram(program);
		
		if (glGetProgrami(program, GL_VALIDATE_STATUS) == GL_FALSE) {
		    System.err.println("ERROR! - Failed to validate shader! [" + vertexShader + " | " + fragmentShader + "]");
		    System.err.println(glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
		    System.exit(1);
		}
		
		return program;
	}
	
	public void setUniform(String uniform, int value) {
		glUniform1i(glGetUniformLocation(program, uniform), value);
	}

	public void setUniform(String uniform, float value) {
		glUniform1f(glGetUniformLocation(program, uniform), value);
	}

	//public void setUniform(String uniform, Vector2f value) {
	//	glUniform2f(glGetUniformLocation(program, uniform), value.x, value.y);
	//}

	public void setUniform(String uniform, Vector3f value) {
		glUniform3f(glGetUniformLocation(program, uniform), value.x, value.y, value.z);
	}

	//public void setUniform(String uniform, Vector4f value) {
	//	glUniform4f(glGetUniformLocation(program, uniform), value.x, value.y, value.z, value.w);
	//}

	public void setUniform(String uniform, Matrix4f value) {
		FloatBuffer elements = MemoryUtil.memAllocFloat(value.getElements().length);
		elements.put(value.getElements()).flip();
		glUniformMatrix4fv(glGetUniformLocation(program, uniform), false, elements);
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void unbind() {
		glUseProgram(0);
	}

	public static String readShader(String filename){
		
		StringBuilder source = new StringBuilder();
		BufferedReader reader = null;
		
		try{
			InputStream input = Shader.class.getResourceAsStream(filename);
			reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while((line = reader.readLine()) != null)
				source.append(line).append("\n");
			reader.close();
		} catch (Exception e){
			System.err.println("ERROR! - Failed to load shader \"" + filename + "\"");
			e.printStackTrace();
		}
		
		return source.toString();
	}
	
	public int getProgram() {
		return program;
	}
	
	public void delete() {
		glDeleteProgram(program);
	}
}
