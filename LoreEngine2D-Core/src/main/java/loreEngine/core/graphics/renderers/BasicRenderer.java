package loreEngine.core.graphics.renderers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL20;

import loreEngine.core.graphics.Camera;
import loreEngine.core.graphics.Renderable;
import loreEngine.core.graphics.Renderer;
import loreEngine.core.graphics.Shader;
import loreEngine.math.Matrix4f;

public class BasicRenderer extends Renderer {

	@Override
	public void render(Renderable renderable, Camera camera, Shader shader) {
		shader.bind();
		
		renderable.getMesh().getVAO().bind();
		renderable.getMesh().getIBO().bind();
		GL20.glEnableVertexAttribArray(0);
		
		shader.setUniform("projection", Matrix4f.Identity());
		shader.setUniform("view", Matrix4f.Identity());
		shader.setUniform("transform", renderable.getTransform());
		
		glDrawElements(GL_TRIANGLES, renderable.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		renderable.getMesh().getIBO().unbind();
		renderable.getMesh().getVAO().unbind();
		
		shader.unbind();
	}

}
