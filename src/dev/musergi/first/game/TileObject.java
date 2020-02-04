package dev.musergi.first.game;

import dev.musergi.first.engine.render.gameobject.GameObject;
import dev.musergi.first.engine.render.gameobject.MeshComponent;
import dev.musergi.first.engine.render.mesh.WavefrontParser;

public class TileObject extends GameObject {
	public TileObject(boolean border) {
		super("Tile");
		if (border) {
			addComponent(WavefrontParser.load("assets/models/exports/grass_border_tile.obj"));
		} else {
			addComponent(WavefrontParser.load("assets/models/exports/grass_tile.obj"));
		}
	}
}
