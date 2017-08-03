package loreEngine.core.graphics.renderer;

import java.util.Stack;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.shader.Shader;
import loreEngine.core.graphics.texture.Texture;
import loreEngine.core.graphics.vertex.Mesh;
import loreEngine.math.Matrix4f;
import loreEngine.math.Vector3f;
import loreEngine.utils.Log;
import loreEngine.utils.LogLevel;

public class BasicBatchRenderer extends BatchRenderer {

	private static final int MAX_RENDERABLES  = 65535;
	private static final int VERTEX_DATA_SIZE = (3 + 4 + 2) * Float.BYTES;
	private static final int MAX_INDICES_SIZE = MAX_RENDERABLES * 6;
	private static final int MAX_BUFFER_SIZE  = MAX_INDICES_SIZE * VERTEX_DATA_SIZE;
	
	protected Stack<Matrix4f> transformStack;
	protected Matrix4f activeTransform;
	
	public BasicBatchRenderer(Texture batchTexture, Camera camera, Shader shader) {
		super(batchTexture, camera, shader);
		this.transformStack = new Stack<Matrix4f>();
		this.transformStack.push(Matrix4f.Identity());
		this.activeTransform = transformStack.peek();
		super.createBatchData(MAX_BUFFER_SIZE, VERTEX_DATA_SIZE, MAX_INDICES_SIZE);
	}

	Mesh mesh = null;
	Vector3f trans = new Vector3f(0, 0, 0);
	
	public void push(Renderable renderable) {	//TODO: Make dynamically
		
		if(vertexBuffer.remaining() == 0) {
			Log.logln(LogLevel.DEBUG, "Batch Renderer buffer overflow; flushing and starting new batch.");
			flush();
		}
		
		mesh = renderable.getMesh();

		activeTransform.storePosVec3f(trans);
		
		vertexBuffer.put(mesh.vertices[0] + trans.x).put(mesh.vertices[1] + trans.y).put(mesh.vertices[2] + trans.z);
		vertexBuffer.put(mesh.colors[0]).put(mesh.colors[1]).put(mesh.colors[2]).put(mesh.colors[3]);
		vertexBuffer.put(mesh.texCoords[0]).put(mesh.texCoords[1]);
		
		vertexBuffer.put(mesh.vertices[3] + trans.x).put(mesh.vertices[4] + trans.y).put(mesh.vertices[5] + trans.z);
		vertexBuffer.put(mesh.colors[4]).put(mesh.colors[5]).put(mesh.colors[6]).put(mesh.colors[7]);
		vertexBuffer.put(mesh.texCoords[2]).put(mesh.texCoords[3]);
		
		vertexBuffer.put(mesh.vertices[6] + trans.x).put(mesh.vertices[7] + trans.y).put(mesh.vertices[8] + trans.z);
		vertexBuffer.put(mesh.colors[8]).put(mesh.colors[9]).put(mesh.colors[10]).put(mesh.colors[11]);
		vertexBuffer.put(mesh.texCoords[4]).put(mesh.texCoords[5]);
		
		vertexBuffer.put(mesh.vertices[9] + trans.x).put(mesh.vertices[10] + trans.y).put(mesh.vertices[11] + trans.z);
		vertexBuffer.put(mesh.colors[12]).put(mesh.colors[13]).put(mesh.colors[14]).put(mesh.colors[15]);
		vertexBuffer.put(mesh.texCoords[6]).put(mesh.texCoords[7]);
		
		indexCount += 6;
	}
	
	public void pushTransform(Matrix4f transform) {
		Matrix4f newTransform = Matrix4f.Identity().mul(transformStack.peek()).mul(transform);
		activeTransform = newTransform;
		transformStack.push(newTransform);
	}
	
	public void popTransform() {
		if(transformStack.size() > 1) transformStack.pop();
		activeTransform = transformStack.peek();
	}

}
