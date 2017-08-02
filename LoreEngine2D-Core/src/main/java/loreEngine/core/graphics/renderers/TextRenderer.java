package loreEngine.core.graphics.renderers;

import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_WRITE_ONLY;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glMapBuffer;
import static org.lwjgl.opengl.GL15.glUnmapBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.nio.ByteOrder;

import org.lwjgl.opengl.GL11;

import loreEngine.addons.text.Glyph;
import loreEngine.addons.text.Text;
import loreEngine.core.graphics.BatchRenderer;
import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.Shader;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class TextRenderer extends BatchRenderer {
	
	private static final int MAX_RENDERABLES  = 65535;
	private static final int VERTEX_DATA_SIZE = (3 + 4 + 2) * Float.BYTES;
	private static final int MAX_INDICES_SIZE = MAX_RENDERABLES * 6;
	private static final int MAX_BUFFER_SIZE  = MAX_INDICES_SIZE * VERTEX_DATA_SIZE;
	
	private float glyphOffset = 0;

	public TextRenderer(Camera camera, Shader shader) {
		this.camera = camera;
		this.shader = shader;
		this.batchTexture = null;
		createBatchData(MAX_BUFFER_SIZE, VERTEX_DATA_SIZE, MAX_INDICES_SIZE);
	}
	
	public void render(Text text) {
		begin();
		this.batchTexture = text.getFont().getTexture();
		for(Glyph glyph : text.getGlyphs()) pushGlyph(glyph, text);
		end(text);
	}
	
	public void pushGlyph(Glyph glyph, Text text) {
		
		if(vertexBuffer.remaining() == 0) {
			Log.logln(LogLevel.DEBUG, "Batch Renderer buffer overflow; flushing and starting new batch.");
			flush(text);
		}
		
		float glyphXpos	  = (float)glyph.x / (float)batchTexture.getWidth();
		float glyphYPos	  = (float)glyph.y / (float)batchTexture.getHeight();
		float glyphWidth  = (float)glyph.width / (float)batchTexture.getWidth();
		float glyphHeight = (float)glyph.height / (float)batchTexture.getHeight();
		
		float size = text.getSize();
		
		float x1 = (float)glyphOffset + ((float)glyph.xOffset / size);
		float y1 = ((float)glyph.yOffset / size);
		
		float x2 = (float)glyphOffset + ((float)glyph.xOffset / size) + ((float)glyph.width / size);
		float y2 = ((float)glyph.yOffset / size);
		
		float x3 = (float)glyphOffset + ((float)glyph.xOffset / size) + ((float)glyph.width / size);
		float y3 = ((float)glyph.yOffset / size) + ((float)glyph.height / size);
		
		float x4 = (float)glyphOffset + ((float)glyph.xOffset / size);
		float y4 = ((float)glyph.yOffset / size) + ((float)glyph.height / size);
		
		float red   = (float)text.getColor().getRed()   / 255.0f;
		float green = (float)text.getColor().getGreen() / 255.0f;
		float blue  = (float)text.getColor().getBlue()  / 255.0f;
		float alpha = (float)text.getColor().getAlpha() / 255.0f;
		
		float tx1 = glyphXpos;
		float ty1 = glyphYPos;
		
		float tx2 = glyphXpos + glyphWidth;
		float ty2 = glyphYPos;
		
		float tx3 = glyphXpos + glyphWidth;
		float ty3 = glyphYPos + glyphHeight;
		
		float tx4 = glyphXpos;
		float ty4 = glyphYPos + glyphHeight;
		
		glyphOffset += (float)glyph.xAdvance / (float)text.getSize();
		
		vertexBuffer.put(x1).put(-y1).put(0);
		vertexBuffer.put(red).put(green).put(blue).put(alpha);
		vertexBuffer.put(tx1).put(ty1);

		vertexBuffer.put(x2).put(-y2).put(0);
		vertexBuffer.put(red).put(green).put(blue).put(alpha);
		vertexBuffer.put(tx2).put(ty2);
		
		vertexBuffer.put(x3).put(-y3).put(0);
		vertexBuffer.put(red).put(green).put(blue).put(alpha);
		vertexBuffer.put(tx3).put(ty3);
		
		vertexBuffer.put(x4).put(-y4).put(0);
		vertexBuffer.put(red).put(green).put(blue).put(alpha);
		vertexBuffer.put(tx4).put(ty4);
		
		indexCount += 6;
		
	}
	
	private void begin() {
		
		if(drawing){
			Log.logln(LogLevel.WARNING, "Attempted to begin batch, but the batch is already drawing.");
			return;
		}
		
		glBindVertexArray(batchVAO);
		glBindBuffer(GL_ARRAY_BUFFER, batchVBO);
		vertexBuffer = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY).order(ByteOrder.nativeOrder()).asFloatBuffer();
		drawing = true;
	}
	
	private void end(Text text) {
		
		if(!drawing){
			Log.logln(LogLevel.WARNING, "Attempted to end batch, but the batch is not drawing.");
			return;
		}
		
		glUnmapBuffer(GL_ARRAY_BUFFER);
		flush(text);
		vertexBuffer.clear();
		glBindVertexArray(0);
		drawing = false;
		
		glyphOffset = 0;
		
		//transformStack.clear();
		//transformStack.push(Matrix4f.Identity());
	}
	
	private void flush(Text text) {
		
		shader.bind();
		
		batchTexture.bind(shader, "vTexture");
		
		shader.setUniform("projection", camera.getProjectionMatrix());
		shader.setUniform("view", camera.getViewMatrix());
		shader.setUniform("transform", text.getTransformMatrix());
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, batchIBO);
		glDrawElements(GL11.GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		batchTexture.unbind();
		
		shader.unbind();
		
		vertexBuffer.clear();
		indexCount = 0;
	}

}
