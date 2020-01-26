package dev.musergi.first.engine.render.mesh;

import org.lwjgl.opengl.GL33;

public class Loader {
	public Mesh loadMesh(float[] positions, float[] normals, int[] indices) {
		int vao = GL33.glGenVertexArrays();
		GL33.glBindVertexArray(vao);
		
		addAttribute(0, 3, positions);
		addAttribute(1, 3, normals);
		addIndices(indices);
		
		return new Mesh(vao, indices.length);
	}
	
	private void addAttribute(int index, int length, float[] data) {
		int buffer = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, buffer);
		GL33.glBufferData(GL33.GL_ARRAY_BUFFER, data, GL33.GL_STATIC_DRAW);
		GL33.glVertexAttribPointer(index, length, GL33.GL_FLOAT, false, 0, 0);
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
	}
	
	private void addIndices(int[] indices) {
		int buffer = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, buffer);
		GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, indices, GL33.GL_STATIC_DRAW);
	}
}
