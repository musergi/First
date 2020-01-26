package dev.musergi.first.engine.render.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
	private Transform transform;
	
	public Camera() {
		transform = new Transform();
	}
	
	public Matrix4f getViewMatrix() {
		return new Matrix4f().identity()
				.scale(transform.getScale())
				.rotateX((float) -Math.toRadians(transform.getRotation().x))
				.rotateY((float) -Math.toRadians(transform.getRotation().y))
				.rotateZ((float) -Math.toRadians(transform.getRotation().z))
				.translate(new Vector3f(transform.getPosition()).mul(-1));
	}
	
	public Transform getTransform() {
		return transform;
	}
}
