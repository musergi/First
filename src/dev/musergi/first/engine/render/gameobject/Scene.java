package dev.musergi.first.engine.render.gameobject;

import java.util.ArrayList;
import java.util.List;

public class Scene {
	protected Camera mainCamera;
	protected List<GameObject> gameObjects;
	
	public Scene() {
		mainCamera = new Camera();
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(mainCamera);
	}
	
	public void update() {
		mainCamera.getComponent(CameraComponent.class).setProjectionMatrix(60f, 0.01f, 100f);
		
		for (GameObject gameObject: gameObjects) {
			gameObject.update();
		}
	}
	
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}
}