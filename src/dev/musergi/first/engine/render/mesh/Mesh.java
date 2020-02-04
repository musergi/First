package dev.musergi.first.engine.render.mesh;

import static org.lwjgl.opengl.GL33.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class Mesh {
	private List<Vector3f> positions;
	private List<Vector3f> normals;
	private List<Vector3i> faces;
	private int vaoId;
	private boolean modified;
	
	public Mesh() {
		positions = new ArrayList<Vector3f>();
		normals = new ArrayList<Vector3f>();
		faces = new ArrayList<Vector3i>();
		vaoId = 0;
		modified = true;
	}
	
	public void addFace(Vector3f v1, Vector3f v2, Vector3f v3) {
		Vector3i face = new Vector3i();
		face.x = positions.size();
		positions.add(v1);
		face.y = positions.size();
		positions.add(v2);
		face.z = positions.size();
		positions.add(v3);
		faces.add(face);
		modified = true;
	}
	
	private void uploadData() {
		float[] positionsArr = new float[positions.size() * 3];
		for (int i = 0; i < positions.size(); i++) {
			Vector3f position = positions.get(i);
			positionsArr[i * 3] = position.x;
			positionsArr[i * 3 + 1] = position.y;
			positionsArr[i * 3 + 2] = position.z;
		}
		
		int[] indices = new int[faces.size() * 3];
		for(int i = 0; i < faces.size(); i++) {
			Vector3i face = faces.get(i);
			indices[i * 3] = face.x;
			indices[i * 3 + 1] = face.y;
			indices[i * 3 + 2] = face.z;
		}
		
		System.out.println(Arrays.toString(positionsArr));
		System.out.println(Arrays.toString(indices));
		
		if (vaoId != 0) {
			glDeleteVertexArrays(vaoId);
		}
		vaoId = Loader.loadMesh(positionsArr, indices);
		modified = false;
		System.out.println("Uploaded mesh with VAO " + vaoId);
	}
	
	public int getId() {
		if (modified) {
			uploadData();
		}
		return vaoId;
	}
	
	public int getVertexCount() {
		return faces.size() * 3;
	}
}
