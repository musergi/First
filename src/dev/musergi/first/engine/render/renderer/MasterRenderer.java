package dev.musergi.first.engine.render.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL33;

import dev.musergi.first.engine.render.entity.AmbientLight;
import dev.musergi.first.engine.render.entity.Camera;
import dev.musergi.first.engine.render.entity.Entity;
import dev.musergi.first.engine.render.entity.PointLight;
import dev.musergi.first.engine.render.entity.Scene;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.window.WindowManager;

public class MasterRenderer {
	private Camera camera;
	private AmbientLight ambientLight;
	private PointLight pointLight;
	
	private EntityRenderer entityRenderer;
	private Map<Mesh, List<Entity>> entities;
	
	public MasterRenderer(Camera camera, AmbientLight ambientLight, PointLight pointLight) {
		this.camera = camera;
		this.ambientLight = ambientLight;
		this.pointLight = pointLight;
		
		entityRenderer = new EntityRenderer();
		entities = new HashMap<Mesh, List<Entity>>();
		GL33.glEnable(GL33.GL_DEPTH_TEST);
	}
	
	public void prepare() {
		GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
		//GL33.glViewport(0, 0, WindowManager.getWidth(), WindowManager.getHeight());
	}
	
	public void render() {
		entityRenderer.setProjectionMatrix();
		
		for (Mesh mesh: entities.keySet()) {
			entityRenderer.render(camera, ambientLight, pointLight, mesh, entities.get(mesh));
		}
		
		entities.clear();
	}
	
	public void submitScene(Scene scene) {
		for (Entity entity: scene.getEntities()) {
			submitEntity(entity);
		}
	}
	
	public void submitEntity(Entity entity) {
		Mesh mesh = entity.getMesh();
		if (entities.containsKey(mesh)) {
			entities.get(mesh).add(entity);
		} else {
			List<Entity> entityList = new ArrayList<Entity>();
			entityList.add(entity);
			entities.put(mesh, entityList);
		}
	}
}
