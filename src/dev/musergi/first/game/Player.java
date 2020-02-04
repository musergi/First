package dev.musergi.first.game;

import dev.musergi.first.engine.render.gameobject.GameObject;
import dev.musergi.first.engine.render.gameobject.MeshComponent;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.render.mesh.WavefrontParser;

public class Player extends GameObject {
	public Player() {
		super("Player");
		addComponent(WavefrontParser.load("assets/models/exports/player.obj"));
	}
}
