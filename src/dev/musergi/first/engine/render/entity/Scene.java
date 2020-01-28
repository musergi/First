package dev.musergi.first.engine.render.entity;

import java.util.ArrayList;
import java.util.List;

public class Scene {
	protected Camera camera;
	protected AmbientLight ambientLight;
	protected PointLight pointLight;
	protected List<Entity> entities;
	
	public Scene(Camera camera, AmbientLight ambientLight, PointLight pointLight) {
		this.camera = camera;
		this.ambientLight = ambientLight;
		this.pointLight = pointLight;
		entities = new ArrayList<Entity>();
	}
	
	protected Scene() {
		entities = new ArrayList<Entity>();
	}

	public Camera getCamera() {
		return camera;
	}

	public AmbientLight getAmbientLight() {
		return ambientLight;
	}

	public PointLight getPointLight() {
		return pointLight;
	}

	public List<Entity> getEntities() {
		return entities;
	}
}
