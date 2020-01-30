package dev.musergi.first.engine.render.gameobject;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform extends Component {
	private Matrix4f transformation;
	
	public Transform() {
		super("Transform");
		transformation = new Matrix4f().identity();
	}
	
	public Vector3f getRotation() {
		Vector3f out = new Vector3f();
		transformation.getEulerAnglesZYX(out);
		return out;
	}
	
	public void reset() {
		transformation.identity();
	}
	
	public void translate(Vector3f vector) {
		transformation.translate(vector);
	}
	
	public void translate(float x, float y, float z) {
		transformation.translate(x, y, z); 
	}
	
	public Vector3f getScale() {
		Vector3f out = new Vector3f();
		transformation.getScale(out);
		return out;
	}

	public void scale(float factor) {
		transformation.scale(factor);
	}
	
	public void setRotation(Vector3f vector) {
		transformation.setRotationXYZ(vector.x, vector.y, vector.z);
	}
	
	public void setRotation(float x, float y, float z) {
		transformation.setRotationXYZ(x, y, z);
	}
	
	public void setPosition(Vector3f vector) {
		transformation.setTranslation(vector);
	}
	
	public void setPosition(float x, float y, float z) {
		transformation.setTranslation(x, y, z);
	}
	
	public Vector3f getPosition() {
		return new Vector3f(transformation.m30(), transformation.m31(), transformation.m32());
	}
	
	public Matrix4f getTransformation() {
		return new Matrix4f(transformation);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		sb.append("(");
		String[] parts = new String[3];
		parts[0] = getPosition().toString();
		parts[1] = getRotation().toString();
		parts[2] = getScale().toString();
		sb.append(String.join(", ", parts));
		sb.append(")");
		return sb.toString();
	}
}
