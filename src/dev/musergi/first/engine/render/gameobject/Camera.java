package dev.musergi.first.engine.render.gameobject;

public class Camera extends GameObject {
	public Camera() {
		super("Camera");
		components.add(new CameraComponent());
	}
}
