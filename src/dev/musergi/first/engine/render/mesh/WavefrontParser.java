package dev.musergi.first.engine.render.mesh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class WavefrontParser {
	public static Mesh load(String filepath, Loader loader) {
		// Read lines
		List<String> lines = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(filepath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// Read info from lines
		List<Vector3f> positionsList = new ArrayList<Vector3f>();
		List<Vector3f> normalList = new ArrayList<Vector3f>();
		List<Integer> indicesList = new ArrayList<Integer>();
		List<Vector3i> createdVertex = new ArrayList<Vector3i>();
		for (String line: lines) {
			if (line.startsWith("v ")) {
				String[] bits = line.split(" ");
				Vector3f position = new Vector3f(
					Float.parseFloat(bits[1]),
					Float.parseFloat(bits[2]),
					Float.parseFloat(bits[3])
				);
				positionsList.add(position);
			} else if (line.startsWith("vn ")) {
				String[] bits = line.split(" ");
				Vector3f normal = new Vector3f(
					Float.parseFloat(bits[1]),
					Float.parseFloat(bits[2]),
					Float.parseFloat(bits[3])
				);
				normalList.add(normal);
			} else if (line.startsWith("f ")) {
				String[] bits = line.substring(2).split(" ");
				for (String bit: bits) {
					Vector3i vertex = parseVertex(bit);
					int index = createdVertex.indexOf(vertex);
					if (index >= 0) {
						indicesList.add(index);
					} else {
						indicesList.add(createdVertex.size());
						createdVertex.add(vertex);
					}
				}
			}
		}
		
		// Create necessary arrays
		float[] positions = new float[createdVertex.size() * 3];
		float[] normals = new float[createdVertex.size() * 3];
		for (int i = 0; i < createdVertex.size(); i++) {
			Vector3f position = positionsList.get(createdVertex.get(i).x - 1);
			positions[i * 3] = position.x;
			positions[i * 3 + 1] = position.y;
			positions[i * 3 + 2] = position.z;
			
			Vector3f normal = normalList.get(createdVertex.get(i).z - 1);
			normals[i * 3] = normal.x;
			normals[i * 3 + 1] = normal.y;
			normals[i * 3 + 2] = normal.z;
		}
		
		int[] indices = new int[indicesList.size()];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = indicesList.get(i);
		}
		
		System.out.println(Arrays.toString(positions));
		System.out.println(Arrays.toString(normals));
		System.out.println(Arrays.toString(indices));
		System.out.println();
		
		return loader.loadMesh(positions, normals, indices);
	}
	
	private static Vector3i parseVertex(String vertexString) {
		String[] bits = vertexString.split("/");
		return new Vector3i(
			Integer.parseInt(bits[0]),
			Integer.parseInt(bits[1]),
			Integer.parseInt(bits[2])
		);
	}
}
