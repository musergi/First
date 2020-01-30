package dev.musergi.first.engine.tester;

import org.joml.Vector3f;

import dev.musergi.first.engine.render.entity.AmbientLight;
import dev.musergi.first.engine.render.entity.Camera;
import dev.musergi.first.engine.render.entity.Entity;
import dev.musergi.first.engine.render.entity.PointLight;
import dev.musergi.first.engine.render.entity.Scene;
import dev.musergi.first.engine.render.mesh.Loader;
import dev.musergi.first.engine.render.mesh.Material;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.render.mesh.WavefrontParser;
import dev.musergi.first.game.entity.Player;
import dev.musergi.first.game.entity.WorldTile;

public class SampleScene extends Scene {

	public SampleScene(Loader loader) {
		camera = new Camera();
		camera.getTransform().getPosition().set(-5f, 5f, 0f);
		camera.getTransform().getRotation().add(-45f, 0f, 0f);
		
		ambientLight = new AmbientLight(new Vector3f(0.5f));
		pointLight = new PointLight(new Vector3f(1f), new Vector3f(-3.5f, 1f, -3.5f), 0.5f, 1.0f, 0.0f, 0.0f);
		
		Material defaultMaterial = new Material();
		Mesh floorMesh = WavefrontParser.load("assets/models/plane.obj", loader);
		Entity floor = new Entity(floorMesh, defaultMaterial);
		floor.getTransform().getPosition().add(-5f, 0, -5f);
		Player player = new Player(loader);
		Entity tile = new WorldTile(loader);
		tile.getTransform().getPosition().add(-12f, -1, -12f);
		
		//entities.add(floor);
		entities.add(player);
		entities.add(tile);
	}
	
}
