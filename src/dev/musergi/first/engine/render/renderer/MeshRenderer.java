package dev.musergi.first.engine.render.renderer;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import dev.musergi.first.engine.Application;
import dev.musergi.first.engine.render.gameobject.CameraComponent;
import dev.musergi.first.engine.render.gameobject.GameObject;
import dev.musergi.first.engine.render.gameobject.MeshComponent;
import dev.musergi.first.engine.render.gameobject.Transform;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.render.shaders.MeshShader;

public class MeshRenderer {
	private MeshShader shader;
	
	public MeshRenderer() {
		shader = new MeshShader();
	}
	
	public void render(MeshComponent meshComponent, GameObject gameObject) {
		shader.bind();
		
		Mesh mesh = meshComponent.getMesh();
		
		CameraComponent camera = Application.getScene().getMainCamera().getComponent(CameraComponent.class);
		shader.setProjectionMatrix(camera.getProjectionMatrix());
		shader.setViewMatrix(camera.getViewMatrix());
		
		Transform tranform = (Transform) gameObject.getComponent(Transform.class);
		shader.setTransformationMatrix(tranform.getTransformation());
		shader.setMaterial(meshComponent.getMaterial());
		
		glBindVertexArray(mesh.getId());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		shader.bind();
	}
}
