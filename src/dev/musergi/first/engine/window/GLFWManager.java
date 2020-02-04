package dev.musergi.first.engine.window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

public class GLFWManager {
	public static void init() {
		if (!glfwInit()) {
			throw new IllegalStateException("Failed to init GLFW.");
		}
	}
	
	public static Window createWindow(int width, int height, String caption) {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		
		
		long window = GLFW.glfwCreateWindow(width, height, caption, NULL, NULL);
		if (window == NULL) {
			throw new RuntimeException("Failed to create window.");
		}
		
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		return new Window(window, width, height);
	}
	
	public static void cleanUp() {
		
	}
}
