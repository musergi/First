package dev.musergi.first.engine.render.utils;

import org.joml.Matrix4f;

import dev.musergi.first.engine.window.WindowManager;

public class MathUtils {
	public static Matrix4f getProjectionMatrix(float yFieldOfView, float zNear, float zFar) {
		float aspectRatio = (float) WindowManager.getWidth() / WindowManager.getHeight();
		return new Matrix4f().setPerspective((float) Math.toRadians(yFieldOfView), aspectRatio, zNear, zFar);
	}
}
