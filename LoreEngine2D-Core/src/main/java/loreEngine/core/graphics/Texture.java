package loreEngine.core.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class Texture {
	
	public enum Wrap {
		REPEAT(GL_REPEAT),
		MIRRORED_REPEAT(GL_MIRRORED_REPEAT),
		CLAMP_TO_EDGE(GL_CLAMP_TO_EDGE),
		CLAMP_TO_BOARDER(GL_CLAMP_TO_BORDER);
		
		private int id = 0;
		
		Wrap(int id) {this.id = id;}
		public int getID() {return id;}
	}
	
	public enum Filter {
		NEAREST(GL_NEAREST),
		LINEAR(GL_LINEAR);
		
		private int id = 0;
		
		Filter(int id) {this.id = id;}
		public int getID() {return id;}
	}
	
	private int width;
	private int height;
	
	private int textureID;
	
	private Wrap wrap;
	private Filter filter;
	
	public Texture(String file) {
		this(file, Wrap.REPEAT, Filter.LINEAR);
	}
	
	public Texture(String file, Wrap wrap, Filter filter) {
		this.textureID = loadTexture(file);
		this.wrap = wrap;
		this.filter = filter;
	}
	
	private int loadTexture(String filename){
		boolean successful = true;
		BufferedImage image;
		try {
			try{
				image = ImageIO.read(Texture.class.getResource(filename));
			} catch(Exception e){
				Log.logln(LogLevel.ERROR, "ERROR! - Unable to read file![" + filename + "]");
				Log.logln(LogLevel.WARNING, "Error reading buffered image, replaced with 'unknown.png'.");
				image = ImageIO.read(Texture.class.getResource("/textures/unknown.png"));
				successful = false;
				e.printStackTrace();
			}

			width = image.getWidth();
			height = image.getHeight();

			int[] pixels = new int[width * height * 4];
			pixels = image.getRGB(0, 0, width, height, null, 0, width);

			ByteBuffer pixelBuffer = BufferUtils.createByteBuffer(width * height * 4);
			
			boolean hasAlpha = image.getColorModel().hasAlpha();

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int pixel = pixels[y * width + x];
					pixelBuffer.put((byte) ((pixel >> 16) & 0xFF)); // RED
					pixelBuffer.put((byte) ((pixel >> 8) & 0xFF)); // GREEN
					pixelBuffer.put((byte) ((pixel >> 0) & 0xFF)); // BLUE
					if(hasAlpha) pixelBuffer.put((byte) ((pixel >> 24) & 0xFF)); // ALPHA
					else pixelBuffer.put((byte)(0xFF));
				}
			}

			pixelBuffer.flip();

			int id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST); //GL_LINEAR
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST); //GL_LINEAR

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelBuffer);

			if(successful)
			Log.logln(LogLevel.INFO, "Texture successfully created: " + filename + " (id:" + id + ")");
			
			return id;

		} catch (IOException e) {
			Log.logln(LogLevel.ERROR, "ERROR! - Unknown error generating textures!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void bind(Shader shader, String fragTextureName) {
		//glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap.getID());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap.getID());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter.getID());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter.getID());
		//shader.setUniform(fragTextureName, 0);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public Wrap getWrap() {
		return wrap;
	}
	
	public Filter getFilter() {
		return filter;
	}

	public int getTextureID() {
		return textureID;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
