package dev.musergi.first.engine.render.mesh;

import org.lwjgl.opengl.GL33;

public class Loader {
	public static int loadMesh(float[] positions, int[] indices) {
		int vao = GL33.glGenVertexArrays();
		GL33.glBindVertexArray(vao);
		
		addAttribute(0, 3, positions);
		addIndices(indices);
		
		return vao;
	}
	
	private static void addAttribute(int index, int length, float[] data) {
		int buffer = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, buffer);
		GL33.glBufferData(GL33.GL_ARRAY_BUFFER, data, GL33.GL_STATIC_DRAW);
		GL33.glVertexAttribPointer(index, length, GL33.GL_FLOAT, false, 0, 0);
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
	}
	
	private static void addIndices(int[] indices) {
		int buffer = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, buffer);
		GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, indices, GL33.GL_STATIC_DRAW);
	}
}
