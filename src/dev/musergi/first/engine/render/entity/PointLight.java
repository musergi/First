package dev.musergi.first.engine.render.entity;

import org.joml.Vector3f;

public class PointLight {
	private Vector3f color;
	private Vector3f position;
	private float intensity;
	private Attenuation attenuation;
	
	public PointLight(Vector3f color, Vector3f position, float intensity, float constantAtt, float linearAtt, float exponentAtt) {
		this.color = color;
		this.position = position;
		this.intensity = intensity;
		attenuation = new Attenuation(constantAtt, linearAtt, exponentAtt);
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	public Attenuation getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Attenuation attenuation) {
		this.attenuation = attenuation;
	}


	public class Attenuation {
		private float constant;
		private float linear;
		private float exponent;
		
		public Attenuation(float constant, float linear, float exponent) {
			this.constant = constant;
			this.linear = linear;
			this.exponent = exponent;
		}

		public float getConstant() {
			return constant;
		}

		public void setConstant(float constant) {
			this.constant = constant;
		}

		public float getLinear() {
			return linear;
		}

		public void setLinear(float linear) {
			this.linear = linear;
		}

		public float getExponent() {
			return exponent;
		}

		public void setExponent(float exponent) {
			this.exponent = exponent;
		}
	}
}
