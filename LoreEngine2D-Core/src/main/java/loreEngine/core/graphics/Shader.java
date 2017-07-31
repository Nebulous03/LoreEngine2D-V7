package loreEngine.core.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shader {
	
	private int program;
	
	public Shader(String vertexShader, String fragmentShader) {
		String vs = readShader(vertexShader);
		String fs = readShader(fragmentShader);
		program = createShader(vs, fs);
	}
	
	public static int createShader(String vertexShader, String fragmentShader) {
		int program = glCreateProgram();
		return program;
	}

	public static String readShader(String filename){
		
		StringBuilder source = new StringBuilder();
		BufferedReader reader = null;
		final String INCLUDE = "#include";
		
		try{
			InputStream input = Shader.class.getResourceAsStream(filename);
			reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while((line = reader.readLine()) != null)
				if(line.startsWith(INCLUDE)){
					source.append(readShader(line.substring(INCLUDE.length() + 2, line.length() -1)));
				} else {
					source.append(line).append("\n");
				}
			reader.close();
		} catch (Exception e){
			//Console.printErr("ERROR LOADING SHADER : " + filename);
			System.err.println("ERROR! - Failed to load shader \"" + filename + "\"");
			e.printStackTrace();
		}
		
		return source.toString();
	}
	
	public int getProgram() {
		return program;
	}
}
