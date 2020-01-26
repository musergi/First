package dev.musergi.first.game.entity;

import org.lwjgl.glfw.GLFW;

import dev.musergi.first.engine.render.color.ColorPalette;
import dev.musergi.first.engine.render.entity.Entity;
import dev.musergi.first.engine.render.mesh.Loader;
import dev.musergi.first.engine.render.mesh.Material;
import dev.musergi.first.engine.render.mesh.WavefrontParser;
import dev.musergi.first.engine.window.WindowManager;

public class Player extends Entity {
	private static final String PLAYER_MESH_PATH = "assets/models/player.obj";
	private static final Material PLAYER_MATERIAL = new Material(ColorPalette.LIGHT_GREEN);
	
	public Player(Loader loader) {
		super(WavefrontParser.load(PLAYER_MESH_PATH, loader), PLAYER_MATERIAL);
		transform.getPosition().add(-5f, 0, -5f);
	}
	
	public void update() {
		if (WindowManager.isPressed(GLFW.GLFW_KEY_A)) {
			transform.getPosition().add(-0.01f, 0f, 0f);
		}
		if (WindowManager.isPressed(GLFW.GLFW_KEY_D)) {
			transform.getPosition().add(0.01f, 0f, 0f);
		}
		if (WindowManager.isPressed(GLFW.GLFW_KEY_S)) {
			transform.getPosition().add(0f, 0, 0.01f);
		}
		if (WindowManager.isPressed(GLFW.GLFW_KEY_W)) {
			transform.getPosition().add(0f, 0, -0.01f);
		}
	}
}
