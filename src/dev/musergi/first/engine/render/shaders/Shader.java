package dev.musergi.first.engine.render.shaders;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL33;

import dev.musergi.first.engine.render.utils.OpenGLUtils;

public abstract class Shader {
	private int programId;
	Map<String, Integer> uniformLocations;
	
	public Shader(String vertexShaderPath, String fragmentShaderPath) {
		programId = GL33.glCreateProgram();
		int vertexShader = compileShader(GL33.GL_VERTEX_SHADER, vertexShaderPath);
		int fragmentShader = compileShader(GL33.GL_FRAGMENT_SHADER, fragmentShaderPath);
		GL33.glAttachShader(programId, vertexShader);
		GL33.glAttachShader(programId, fragmentShader);
		GL33.glLinkProgram(programId);
		OpenGLUtils.checkProgramLinking(programId);
		uniformLocations = new HashMap<String, Integer>();
		getAllUniformLocations();
	}
	
	private int compileShader(int type, String sourcePath) {
		int shader = GL33.glCreateShader(type);
		GL33.glShaderSource(shader, OpenGLUtils.readShaderSource(sourcePath));
		GL33.glCompileShader(shader);
		OpenGLUtils.checkShaderCompilation(shader);
		return shader;
	}
	
	protected abstract void getAllUniformLocations();
	
	protected void getUniformLocation(String name) {
		int location = GL33.glGetUniformLocation(programId, name);
		if (location == -1) {
			System.out.println("Failed to get location of " + name);
		}
		uniformLocations.put(name, location);
	}
	
	public void setUniform(String name, int value) {
		int location = uniformLocations.get(name);
		GL33.glUniform1i(location, value);
	}
	
	public void setUniform(String name, boolean value) {
		int location = uniformLocations.get(name);
		GL33.glUniform1i(location, (value ? 1 : 0));
	}
	
	public void setUniform(String name, float value) {
		int location = uniformLocations.get(name);
		GL33.glUniform1f(location, value);
	}
	
	public void setUniform(String name, Vector3f value) {
		int location = uniformLocations.get(name);
		GL33.glUniform3f(location, value.x, value.y, value.z);
	}
	
	public void setUniform(String name, Matrix4f value) {
		int location = uniformLocations.get(name);
		float[] data = new float[4 * 4];
		value.get(data);
		GL33.glUniformMatrix4fv(location, false, data);
	}
	
	public void bind() {
		GL33.glUseProgram(programId);
	}
	
	public void unbind() {
		GL33.glUseProgram(0);
	}
}
