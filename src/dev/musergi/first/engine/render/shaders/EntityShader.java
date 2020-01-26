package dev.musergi.first.engine.render.shaders;

import org.joml.Matrix4f;

import dev.musergi.first.engine.render.entity.AmbientLight;
import dev.musergi.first.engine.render.entity.PointLight;
import dev.musergi.first.engine.render.mesh.Material;

public class EntityShader extends Shader {
	private static final String VERTEX_PATH = "assets/shaders/entity_vs.glsl";
	private static final String FRAGMENT_PATH = "assets/shaders/entity_fs.glsl";
	
	private static final String PROJECTION_MATRIX_NAME = "u_projection_matrix";
	private static final String VIEW_MATRIX_NAME = "u_view_matrix";
	private static final String MODEL_MATRIX_NAME = "u_model_matrix";
	private static final String AMBIENT_COLOR_NAME = "u_ambient_light_color";
	private static final String MATERIAL_AMBIENT_COLOR_NAME = "u_material.ambient_color";
	private static final String MATERIAL_DIFFUSE_COLOR_NAME = "u_material.diffuse_color";
	private static final String MATERIAL_SPECULAR_COLOR_NAME = "u_material.specular_color";
	private static final String MATERIAL_SPECULAR_EXPONENT_NAME = "u_material.specular_exponent";
	private static final String POINT_LIGHT_COLOR_NAME = "u_point_light.color";
	private static final String POINT_LIGHT_POSITION_NAME = "u_point_light.position";
	private static final String POINT_LIGHT_INTENSITY_NAME = "u_point_light.intensity";
	
	public EntityShader() {
		super(VERTEX_PATH, FRAGMENT_PATH);
	}

	@Override
	protected void getAllUniformLocations() {
		getUniformLocation(PROJECTION_MATRIX_NAME);
		getUniformLocation(VIEW_MATRIX_NAME);
		getUniformLocation(MODEL_MATRIX_NAME);
		getUniformLocation(AMBIENT_COLOR_NAME);
		
		getUniformLocation(MATERIAL_AMBIENT_COLOR_NAME);
		getUniformLocation(MATERIAL_DIFFUSE_COLOR_NAME);
		getUniformLocation(MATERIAL_SPECULAR_COLOR_NAME);
		getUniformLocation(MATERIAL_SPECULAR_EXPONENT_NAME);
		
		getUniformLocation(POINT_LIGHT_COLOR_NAME);
		getUniformLocation(POINT_LIGHT_POSITION_NAME);
		getUniformLocation(POINT_LIGHT_INTENSITY_NAME);
	}

	public void setProjectionMatrix(Matrix4f matrix) {
		setUniform(PROJECTION_MATRIX_NAME, matrix);
	}
	
	public void setViewMatrix(Matrix4f matrix) {
		setUniform(VIEW_MATRIX_NAME, matrix);
	}
	
	public void setModelMatrix(Matrix4f matrix) {
		setUniform(MODEL_MATRIX_NAME, matrix);
	}
	
	public void setMaterial(Material material) {
		setUniform(MATERIAL_AMBIENT_COLOR_NAME, material.getAmbientColor());
		setUniform(MATERIAL_DIFFUSE_COLOR_NAME, material.getDiffuseColor());
		setUniform(MATERIAL_SPECULAR_COLOR_NAME, material.getSpecularColor());
		setUniform(MATERIAL_SPECULAR_EXPONENT_NAME, material.getSpecularExponent());
	}
	
	public void setAmbientLight(AmbientLight light) {
		setUniform(AMBIENT_COLOR_NAME, light.getColor());
	}
	
	public void setPointLight(PointLight pointLight) {
		setUniform(POINT_LIGHT_COLOR_NAME, pointLight.getColor());
		setUniform(POINT_LIGHT_POSITION_NAME, pointLight.getPosition());
		setUniform(POINT_LIGHT_INTENSITY_NAME, pointLight.getIntensity());
	}
}
