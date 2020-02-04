package dev.musergi.first.engine.window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

public class Window {
	private long window;
	private int width, height;

	public Window(long window, int initialWidth, int initialHeight) {
		this.window = window;
		width = initialWidth;
		height = initialHeight;
		glfwSetWindowSizeCallback(window, (win, width, height) -> {
			glViewport(0, 0, width, height);
			this.width = width;
			this.height = height;
		});
	}

	public void update() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
