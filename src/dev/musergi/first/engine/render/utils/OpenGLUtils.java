package dev.musergi.first.engine.render.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL33;

public class OpenGLUtils {
	public static void checkErrors() {
		int errorCode;
		while ((errorCode = GL33.glGetError()) != 0) {
			System.out.println("Found OpenGL error with code: " + errorCode);
		}
	}

	public static String readShaderSource(String path) {
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append('\n');
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void checkShaderCompilation(int shader) {
		if (GL33.glGetShaderi(shader, GL33.GL_COMPILE_STATUS) == GL33.GL_FALSE) {
			System.out.println("Failed to compile shader.");
			System.out.println(GL33.glGetShaderInfoLog(shader));
		}
	}
	
	public static void checkProgramLinking(int program) {
		if (GL33.glGetProgrami(program, GL33.GL_LINK_STATUS) == GL33.GL_FALSE) {
			System.out.println("Failed to link shader program.");
			System.out.println(GL33.glGetProgramInfoLog(program));
		}
	}
}
