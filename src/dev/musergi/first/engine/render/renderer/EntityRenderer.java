package dev.musergi.first.engine.render.renderer;

import java.util.List;

import org.lwjgl.opengl.GL33;

import dev.musergi.first.engine.render.entity.AmbientLight;
import dev.musergi.first.engine.render.entity.Camera;
import dev.musergi.first.engine.render.entity.Entity;
import dev.musergi.first.engine.render.entity.PointLight;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.render.shaders.EntityShader;
import dev.musergi.first.engine.render.utils.MathUtils;
import dev.musergi.first.engine.window.WindowManager;

public class EntityRenderer {
	private static final float FOVY = 60.0f;
	private static final float Z_NEAR = 0.1f;
	private static final float Z_FAR = 100.0f;
	
	private EntityShader entityShader;
	
	public EntityRenderer() {
		 entityShader = new EntityShader();
		 GL33.glEnable(GL33.GL_DEPTH_TEST);
	}
	
	public void prepare() {
		GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
		GL33.glViewport(0, 0, WindowManager.getWidth(), WindowManager.getHeight());
	}
	
	public void render(Camera camera, AmbientLight ambientLight, PointLight pointLight, Entity entity) {
		entityShader.bind();
		entityShader.setViewMatrix(camera.getViewMatrix());
		entityShader.setModelMatrix(entity.getTransform().getMatrix());
		
		entityShader.setMaterial(entity.getMaterial());
		entityShader.setAmbientLight(ambientLight);
		entityShader.setPointLight(pointLight);
		
		Mesh mesh = entity.getMesh();
		GL33.glBindVertexArray(mesh.getVao());
		GL33.glEnableVertexAttribArray(0);
		GL33.glEnableVertexAttribArray(1);
		GL33.glDrawElements(GL33.GL_TRIANGLES, mesh.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
		GL33.glDisableVertexAttribArray(0);
		GL33.glDisableVertexAttribArray(1);
		GL33.glBindVertexArray(0);
		
		entityShader.unbind();
	}
	
	public void render(Camera camera, AmbientLight ambientLight, PointLight pointLight, Mesh mesh, List<Entity> entities) {
		entityShader.bind();
		entityShader.setViewMatrix(camera.getViewMatrix());
		entityShader.setAmbientLight(ambientLight);
		entityShader.setPointLight(pointLight);
		
		GL33.glBindVertexArray(mesh.getVao());
		GL33.glEnableVertexAttribArray(0);
		GL33.glEnableVertexAttribArray(1);
		
		for (Entity entity: entities) {
			entityShader.setMaterial(entity.getMaterial());
			entityShader.setModelMatrix(entity.getTransform().getMatrix());
			GL33.glDrawElements(GL33.GL_TRIANGLES, mesh.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
		}
		
		GL33.glDisableVertexAttribArray(0);
		GL33.glDisableVertexAttribArray(1);
		GL33.glBindVertexArray(0);
		
		entityShader.unbind();
	}

	public void setProjectionMatrix() {
		entityShader.bind();
		entityShader.setProjectionMatrix(MathUtils.getProjectionMatrix(FOVY, Z_NEAR, Z_FAR));
	}
}
