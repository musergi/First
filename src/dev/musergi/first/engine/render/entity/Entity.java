package dev.musergi.first.engine.render.entity;

import dev.musergi.first.engine.render.mesh.Material;
import dev.musergi.first.engine.render.mesh.Mesh;

public class Entity {
	protected Transform transform;
	private Mesh mesh;
	private Material material;
	
	public Entity(Mesh mesh, Material material) {
		transform = new Transform();
		this.mesh = mesh;
		this.material = material;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}
}
