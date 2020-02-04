package dev.musergi.first.engine.render.renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.musergi.first.engine.render.gameobject.GameObject;
import dev.musergi.first.engine.render.gameobject.MeshComponent;

public class Renderer {
	private MeshRenderer meshRenderer;
	private Map<MeshComponent, List<GameObject>> meshes;
	
	public Renderer() {
		glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		glEnable(GL_DEPTH_TEST);
		
		meshRenderer = new MeshRenderer();
		meshes = new HashMap<MeshComponent, List<GameObject>>();
	}
	
	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void render() {
		for (MeshComponent mesh: meshes.keySet()) {
			for (GameObject gameObject: meshes.get(mesh)) {
				meshRenderer.render(mesh, gameObject);
			}
		}
		meshes.clear();
	}
	
	public void submitMesh(MeshComponent mesh, GameObject gameObject) {
		if (meshes.containsKey(mesh)) {
			meshes.get(mesh).add(gameObject);
		} else {
			List<GameObject> gameObjects = new ArrayList<GameObject>();
			gameObjects.add(gameObject);
			meshes.put(mesh, gameObjects);
		}
	}
}
