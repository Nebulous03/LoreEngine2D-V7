package loreEngine.core.graphics.renderer;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

import org.lwjgl.opengl.GL20;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.shader.Shader;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class BasicRenderer extends Renderer {
	
	public BasicRenderer(Camera camera, Shader shader) {
		super(camera, shader);
	}

	public void render(Renderable renderable) {
		
		if(renderable.getMesh() == null){
			Log.logln(LogLevel.DEBUG, "Renderable was passed into renderer with null mesh");
			return;
		}
		
		shader.bind();		
		
		renderable.getMesh().getVAO().bind();
		renderable.getMesh().getIBO().bind();
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		shader.setUniform("projection", camera.getProjectionMatrix());
		shader.setUniform("view", camera.getViewMatrix());
		shader.setUniform("transform", renderable.getTransformMatrix());
		
		renderable.getTexture().bind(shader, "vTexture");
		
		glDrawElements(GL_TRIANGLES, renderable.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);
		
		renderable.getTexture().unbind();
		
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		renderable.getMesh().getIBO().unbind();
		renderable.getMesh().getVAO().unbind();
		
		shader.unbind();
	}

}
