package dev.musergi.first.engine.render.mesh;

import org.joml.Vector3f;

public class Material {
	private static final Vector3f DEFAULT_AMBIENT_COLOR = new Vector3f(1.0f);
	private static final Vector3f DEFAULT_DIFFUSE_COLOR = new Vector3f(1.0f);
	private static final Vector3f DEFAULT_SPECULAR_COLOR = new Vector3f(1.0f);
	private static final float DEFAULT_SPECULAR_EXPONENT = 500.0f;
	
	private Vector3f ambientColor;
	private Vector3f diffuseColor;
	private Vector3f specularColor;
	private float specularExponent;
	
	public Material(Vector3f ambientColor, Vector3f diffuseColor, Vector3f specularColor, float specularExponent) {
		this.ambientColor = ambientColor;
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.specularExponent = specularExponent;
	}
	
	public Material(Vector3f color) {
		this(color, color, color, DEFAULT_SPECULAR_EXPONENT);
	}
	
	public Material() {
		this(DEFAULT_AMBIENT_COLOR, DEFAULT_DIFFUSE_COLOR, DEFAULT_SPECULAR_COLOR, DEFAULT_SPECULAR_EXPONENT);
	}

	public Vector3f getAmbientColor() {
		return ambientColor;
	}

	public Vector3f getDiffuseColor() {
		return diffuseColor;
	}

	public Vector3f getSpecularColor() {
		return specularColor;
	}

	public float getSpecularExponent() {
		return specularExponent;
	}
}
