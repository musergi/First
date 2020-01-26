package dev.musergi.first.engine.tester;

import org.joml.Vector3f;

import dev.musergi.first.engine.render.EntityRenderer;
import dev.musergi.first.engine.render.color.ColorPalette;
import dev.musergi.first.engine.render.entity.AmbientLight;
import dev.musergi.first.engine.render.entity.Camera;
import dev.musergi.first.engine.render.entity.Entity;
import dev.musergi.first.engine.render.entity.PointLight;
import dev.musergi.first.engine.render.mesh.Loader;
import dev.musergi.first.engine.render.mesh.Material;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.render.mesh.WavefrontParser;
import dev.musergi.first.engine.window.WindowManager;
import dev.musergi.first.game.entity.Player;

public class Test {
	public static void main(String[] args) {
		WindowManager.createWindow();
		
		Loader loader = new Loader();
		
		Material defaultMaterial = new Material();
		
		Mesh floorMesh = WavefrontParser.load("assets/models/plane.obj", loader);
		Entity floor = new Entity(floorMesh, defaultMaterial);
		floor.getTransform().getPosition().add(-5f, 0, -5f);

		Player player = new Player(loader);
		
		EntityRenderer entityRenderer = new EntityRenderer();
		
		Camera camera = new Camera();
		camera.getTransform().getPosition().set(-5f, 5f, 0f);
		camera.getTransform().getRotation().add(-45f, 0f, 0f);
		
		AmbientLight light = new AmbientLight(new Vector3f(0.5f));
		PointLight pointLight = new PointLight(new Vector3f(1f), new Vector3f(-3.5f, 1f, -3.5f), 0.5f, 1.0f, 0.0f, 0.0f);
		
		while (!WindowManager.shouldClose()) {
			entityRenderer.prepare();
			
			entityRenderer.setProjectionMatrix();
			entityRenderer.render(camera, light, pointLight, player);
			entityRenderer.render(camera, light, pointLight, floor);
			
			player.update();
			
			WindowManager.updateWindow();
		}
		
		WindowManager.cleanUp();
	}
}
