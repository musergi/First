package dev.musergi.first.engine;

import dev.musergi.first.engine.render.gameobject.Scene;
import dev.musergi.first.engine.render.renderer.Renderer;
import dev.musergi.first.engine.window.GLFWManager;
import dev.musergi.first.engine.window.Window;

public class Application {
	private static Window window;
	private static Renderer renderer;
	private static Scene scene;
	
	public static void init() {
		GLFWManager.init();
		window = GLFWManager.createWindow(800, 600, "Initial caption");
		renderer = new Renderer();
	}
	
	public static void loop() {
		while (!window.shouldClose()) {
			scene.update();
			
			renderer.prepare();
			renderer.render();
			
			window.update();
		}
		GLFWManager.cleanUp();
	}
	
	public static Window getWindow() {
		return window;
	}
	
	public static Renderer getRenderer() {
		return renderer;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		Application.scene = scene;
	}
}
