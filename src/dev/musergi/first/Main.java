package dev.musergi.first;

import org.joml.Vector3f;

import dev.musergi.first.engine.Application;
import dev.musergi.first.engine.render.gameobject.GameObject;
import dev.musergi.first.engine.render.gameobject.MeshComponent;
import dev.musergi.first.engine.render.gameobject.Scene;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.game.SampleScene;

public class Main {

	public static void main(String[] args) {
		Application.init();
		
		Scene scene = new SampleScene();
		Application.setScene(scene);
		
		Application.loop();
	}

}
