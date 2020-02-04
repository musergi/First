package dev.musergi.first.engine.render.gameobject;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import dev.musergi.first.engine.Application;
import dev.musergi.first.engine.window.Window;

public class CameraComponent extends Component {
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;

	public CameraComponent() {
		super("Camera");
		projectionMatrix = new Matrix4f().identity();
		viewMatrix = new Matrix4f().identity();
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public void setProjectionMatrix(Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}
	
	public void setProjectionMatrix(float fovy, float zNear, float zFar) {
		Window window = Application.getWindow();
		float aspect = (float) window.getWidth() / window.getHeight();
		projectionMatrix = new Matrix4f().setPerspective((float) Math.toRadians(fovy), aspect, zNear, zFar);
	}

	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	public void setViewMatrix(Matrix4f viewMatrix) {
		this.viewMatrix = viewMatrix;
	}
	
	public void lookAt(Vector3f position, Vector3f target, Vector3f up) {
		viewMatrix = new Matrix4f().lookAt(position, target, up);
	}
}
