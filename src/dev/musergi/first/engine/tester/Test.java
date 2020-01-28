package dev.musergi.first.engine.tester;

import dev.musergi.first.engine.render.entity.Scene;
import dev.musergi.first.engine.render.mesh.Loader;
import dev.musergi.first.engine.render.renderer.MasterRenderer;
import dev.musergi.first.engine.window.WindowManager;

public class Test {
	public static void main(String[] args) {
		WindowManager.createWindow();
		
		Loader loader = new Loader();
		Scene scene = new SampleScene(loader);
		MasterRenderer renderer = new MasterRenderer(scene.getCamera(), scene.getAmbientLight(), scene.getPointLight());
		
		while (!WindowManager.shouldClose()) {
			renderer.prepare();
			
			renderer.submitScene(scene);
			renderer.render();
			
			WindowManager.updateWindow();
		}
		
		WindowManager.cleanUp();
	}
}
