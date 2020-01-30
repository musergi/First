package dev.musergi.first.engine;

import dev.musergi.first.engine.render.renderer.Renderer;
import dev.musergi.first.engine.window.GLFWManager;
import dev.musergi.first.engine.window.Window;

public class Application {
	public static Window window;
	public static Renderer renderer;
	
	public static void init() {
		GLFWManager.init();
		window = GLFWManager.createWindow(800, 600, "Initial caption");
		
	}
}
