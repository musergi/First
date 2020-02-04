package dev.musergi.first.game;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;
import org.joml.Vector3f;

import dev.musergi.first.engine.render.gameobject.CameraComponent;
import dev.musergi.first.engine.render.gameobject.Scene;
import dev.musergi.first.engine.render.gameobject.Transform;

public class SampleScene extends Scene {
	private Map<Vector2i, TileObject> tiles;
	
	public SampleScene() {
		createTiles();
		gameObjects.add(new Player());
		
		CameraComponent cameraComponent = mainCamera.getComponent(CameraComponent.class);
		cameraComponent.lookAt(new Vector3f(0f, 3f, 3f), new Vector3f(), new Vector3f(0f, 1f, 0f));
	}
	
	private void createTiles() {
		tiles = new HashMap<Vector2i, TileObject>();
		
		int max = 10;
		for (int i = -1; i < max + 1; i++) {
			for (int j = -1; j < max + 1; j++) {
				TileObject newTile = new TileObject(i == -1 || i == max || j == -1 || j == max);
				newTile.getComponent(Transform.class).setPosition(i, 0, -j);
				tiles.put(new Vector2i(i, j), newTile);
				gameObjects.add(newTile);
			}
		}
	}
}
