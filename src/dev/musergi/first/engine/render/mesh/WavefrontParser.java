package dev.musergi.first.engine.render.mesh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dev.musergi.first.engine.render.gameobject.MeshComponent;

public class WavefrontParser {
	private static Map<String, ParsedFile> parsedMeshes = new HashMap<String, ParsedFile>();
	private static class ParsedFile {
		public Mesh mesh;
		public Material material;
		
		public ParsedFile(Mesh mesh, Material material) {
			this.mesh = mesh;
			this.material = material;
		}
	}
			
	public static MeshComponent load(String filepath) {
		// Return cache
		if (parsedMeshes.containsKey(filepath)) {
			ParsedFile file = parsedMeshes.get(filepath);
			return new MeshComponent(file.mesh, file.material);
		}
		
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
		Mesh mesh = new Mesh();
		Material material = new Material();
		for (String line: lines) {
			if (line.startsWith("v ")) {
				String[] bits = line.split(" ");
				Vector3f position = new Vector3f(
					Float.parseFloat(bits[1]),
					Float.parseFloat(bits[2]),
					Float.parseFloat(bits[3])
				);
				positionsList.add(position);
			} else if (line.startsWith("f ")) {
				String[] bits = line.substring(2).split(" ");
				Vector3f v1 = positionsList.get(parseVertex(bits[0]).x - 1);
				Vector3f v2 = positionsList.get(parseVertex(bits[1]).x - 1);
				Vector3f v3 = positionsList.get(parseVertex(bits[2]).x - 1);
				mesh.addFace(v1, v2, v3);
			} else if (line.startsWith("mtllib")) {
				Path path = Paths.get(filepath);
				path = path.subpath(0, path.getNameCount() - 1);
				String materialPath = Paths.get(path.toString(), line.split(" ")[1]).toString();
				parseMaterial(materialPath, material);
			}
		}
		
		parsedMeshes.put(filepath, new WavefrontParser.ParsedFile(mesh, material));
		return new MeshComponent(mesh, material);
	}
	
	private static void parseMaterial(String filepath, Material out) {
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
		}
		
		// Parse material
		for (String line: lines) {
			if (line.startsWith("Ka ")) {
				String[] bits = line.split(" ");
				Vector3f ambientColor = new Vector3f(
					Float.parseFloat(bits[1]),
					Float.parseFloat(bits[2]),
					Float.parseFloat(bits[3])
				);
				out.setAmbientColor(ambientColor);
			} else if (line.startsWith("Kd ")) {
				String[] bits = line.split(" ");
				Vector3f diffuseColor = new Vector3f(
					Float.parseFloat(bits[1]),
					Float.parseFloat(bits[2]),
					Float.parseFloat(bits[3])
				);
				out.setDiffuseColor(diffuseColor);
			} else if (line.startsWith("Ks ")) {
				String[] bits = line.split(" ");
				Vector3f specularColor = new Vector3f(
					Float.parseFloat(bits[1]),
					Float.parseFloat(bits[2]),
					Float.parseFloat(bits[3])
				);
				out.setSpecularColor(specularColor);
			} else if (line.startsWith("Ns ")) {
				String[] bits = line.split(" ");
				out.setSpecularExponent(Float.parseFloat(bits[1]));
			}
		}
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
