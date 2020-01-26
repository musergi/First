package dev.musergi.first.engine.render.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	public Transform() {
		position = new Vector3f();
		rotation = new Vector3f();
		scale = 1.0f;
	}

	public Matrix4f getMatrix() {
		return new Matrix4f().identity()
				.translate(position)
				.rotateX((float) Math.toRadians(rotation.x))
				.rotateY((float) Math.toRadians(rotation.y))
				.rotateZ((float) Math.toRadians(rotation.z))
				.scale(scale);
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
