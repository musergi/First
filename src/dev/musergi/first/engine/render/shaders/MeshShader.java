package dev.musergi.first.engine.render.shaders;

import org.joml.Matrix4f;

import dev.musergi.first.engine.render.mesh.Material;

public class MeshShader extends Shader {
	private static final String VS_PATH = "assets/shaders/mesh_vs.glsl";
	private static final String FS_PATH = "assets/shaders/mesh_fs.glsl";
	
	public MeshShader() {
		super(VS_PATH, FS_PATH);
	}

	@Override
	protected void getAllUniformLocations() {
		getUniformLocation("u_projection_matrix");
		getUniformLocation("u_view_matrix");
		getUniformLocation("u_transformation_matrix");
		
		getUniformLocation("u_material.ambient_color");
		getUniformLocation("u_material.diffuse_color");
		getUniformLocation("u_material.specular_color");
		getUniformLocation("u_material.specular_exp");
	}
	
	public void setProjectionMatrix(Matrix4f matrix) {
		setUniform("u_projection_matrix", matrix);
	}
	
	public void setViewMatrix(Matrix4f matrix) {
		setUniform("u_view_matrix", matrix);
	}
	
	public void setTransformationMatrix(Matrix4f matrix) {
		setUniform("u_transformation_matrix", matrix);
	}
	
	public void setMaterial(Material material) {
		setUniform("u_material.ambient_color", material.getAmbientColor());
		setUniform("u_material.diffuse_color", material.getDiffuseColor());
		setUniform("u_material.specular_color", material.getSpecularColor());
		setUniform("u_material.specular_exp", material.getSpecularExponent());
	}
}
