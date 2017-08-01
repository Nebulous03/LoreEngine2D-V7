package loreEngine.core.graphics.renderers;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

import org.lwjgl.opengl.GL20;

import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Renderer;
import loreEngine.core.graphics.Shader;

public class BasicRenderer extends Renderer {

	@Override
	public void render(Renderable renderable, Camera camera, Shader shader) {
		shader.bind();
		
		renderable.getMesh().getVAO().bind();
		renderable.getMesh().getIBO().bind();
		GL20.glEnableVertexAttribArray(0);
		
		shader.setUniform("projection", camera.getProjectionMatrix());//Matrix4f.Perspective(90.0f, 16.0f/9.0f, 0.0001f, 1000.0f));
		shader.setUniform("view", camera.getViewMatrix());
		shader.setUniform("transform", renderable.getTransformMatrix());
		
		glDrawElements(GL_TRIANGLES, renderable.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		renderable.getMesh().getIBO().unbind();
		renderable.getMesh().getVAO().unbind();
		
		shader.unbind();
	}

}
