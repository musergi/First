package dev.musergi.first.engine.window;

import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

public class WindowManager {
	private static final int INITIAL_WIDTH = 800;
	private static final int INITIAL_HEIGHT = 600;
	private static final String INITIAL_CAPTION = "Initial caption";
	
	private static long window;
	private static int width = INITIAL_WIDTH;
	private static int height = INITIAL_HEIGHT;
	
	public static void createWindow() {
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to init GLFW.");
		}
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		
		
		window = GLFW.glfwCreateWindow(INITIAL_WIDTH, INITIAL_HEIGHT, INITIAL_CAPTION, NULL, NULL);
		if (window == NULL) {
			throw new RuntimeException("Failed to create window.");
		}
		
		GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE )
				GLFW.glfwSetWindowShouldClose(window, true);
		});
		
		GLFW.glfwSetWindowSizeCallback(window, (window, width, height) -> {
			WindowManager.width = width;
			WindowManager.height = height;
		});
		
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
	}
	
	public static void updateWindow() {
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
	}
	
	public static void cleanUp() {
		GLFW.glfwTerminate();
	}
	
	public static boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static boolean isPressed(int key) {
		int keyState = GLFW.glfwGetKey(window, key);
		return keyState == GLFW.GLFW_PRESS || keyState == GLFW.GLFW_REPEAT;
	}
}
