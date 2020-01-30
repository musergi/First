package dev.musergi.first.engine.tester;

import dev.musergi.first.engine.render.gameobject.Transform;

public class TransformTest {
	public static void main(String[] args) {
		Transform transform = new Transform();
		System.out.println(transform);
		transform.setPosition(1, 2, 3);
		System.out.println(transform);
		transform.setRotation(1, 0, 0);
		System.out.println(transform);
		
	}
}
