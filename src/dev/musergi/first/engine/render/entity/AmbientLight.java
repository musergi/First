package dev.musergi.first.engine.render.entity;

import org.joml.Vector3f;

public class AmbientLight {
	private Vector3f color;
	
	public AmbientLight(Vector3f color) {
		this.color = color;
	}
	
	public Vector3f getColor() {
		return color;
	}
}
