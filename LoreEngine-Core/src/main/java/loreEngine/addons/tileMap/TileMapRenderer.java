package loreEngine.addons.tileMap;

import loreEngine.core.graphics.camera.Camera;
import loreEngine.core.graphics.renderer.BatchRenderer;
import loreEngine.core.graphics.shader.Shader;

public class TileMapRenderer extends BatchRenderer {
	
	private static final int MAX_RENDERABLES  = 65535;
	private static final int VERTEX_DATA_SIZE = (3 + 4 + 2) * Float.BYTES;
	private static final int MAX_INDICES_SIZE = MAX_RENDERABLES * 6;
	private static final int MAX_BUFFER_SIZE  = MAX_INDICES_SIZE * VERTEX_DATA_SIZE;
	
	public TileMesh mesh;
	
	public TileMapRenderer(Camera camera, Shader shader) {
		super(null, camera, shader);
		this.mesh = new TileMesh();
		this.createBatchData(MAX_BUFFER_SIZE, VERTEX_DATA_SIZE, MAX_INDICES_SIZE);
	}
	
	public void render(TileMap map) {
		begin();
		
		if(map.isCullingEnabled()) {
			
			int posXStart = (int) (camera.getPosition().x - (map.getCullWidth() / 2) + 0.5f);
			int posYStart = (int) (camera.getPosition().y - (map.getCullHeight() / 2) + 0.5f);
			
			if(posXStart + map.getCullWidth() > map.getWidth()) posXStart = map.getWidth() - map.getCullWidth();
			if(posYStart + map.getCullHeight() > map.getHeight()) posYStart = map.getHeight() - map.getCullHeight();
			
			if(posYStart < 0) posYStart = 0;
			if(posXStart < 0) posXStart = 0;
			
			for(int y = posYStart; y < posYStart + map.getCullHeight(); y++) {
				for(int x = posXStart; x < posXStart + map.getCullWidth(); x++) {
					if(map.get(x, y) == null) continue;
					addToVBO (
						mesh.vertices,
						mesh.colors,
						x, y, 0,
						((float)map.get(x, y).texX * (float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getWidth()) + 0.5f / (float)map.getSpriteMap().getTexture().getWidth(),
						((float)map.get(x, y).texY * (float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getWidth()) + 0.5f / (float)map.getSpriteMap().getTexture().getHeight(),
						((float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getWidth()) - 0.5f / (float)map.getSpriteMap().getTexture().getWidth(),
						((float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getHeight()) - 0.5f / (float)map.getSpriteMap().getTexture().getHeight()
					);
				}
			}
			
		} else {
			
			for(int y = 0; y < map.getHeight(); y++) {
				for(int x = 0; x < map.getWidth(); x++) {
					addToVBO (
						mesh.vertices,
						mesh.colors,
						x, y, 0,
						((float)map.get(x, y).texX * (float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getWidth()) + 0.5f / (float)map.getSpriteMap().getTexture().getWidth(),
						((float)map.get(x, y).texY * (float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getWidth()) + 0.5f / (float)map.getSpriteMap().getTexture().getHeight(),
						((float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getWidth()) - 0.5f / (float)map.getSpriteMap().getTexture().getWidth(),
						((float)map.getSpriteMap().getSpriteSize() / (float)map.getSpriteMap().getTexture().getHeight()) - 0.5f / (float)map.getSpriteMap().getTexture().getHeight()
					);
				}
			}
		
		}
		
		map.getSpriteMap().getTexture().bind(shader, "vTexture");
		
		end();
		
		map.getSpriteMap().getTexture().unbind();
	}

}
