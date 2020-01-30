package dev.musergi.first.game.entity;

import org.joml.Vector3f;

import dev.musergi.first.engine.render.entity.Entity;
import dev.musergi.first.engine.render.mesh.Loader;
import dev.musergi.first.engine.render.mesh.Material;
import dev.musergi.first.engine.render.mesh.Mesh;

public class WorldTile extends Entity {
	private static final Material TILE_MATERIAL = new Material();
	
	public WorldTile(Loader loader) {
		super(generateMesh(loader), TILE_MATERIAL);
	}
	
	private static Mesh generateMesh(Loader loader) {
		float[] positions = {
			0, 1, 0, // -x +y -z 0
			10, 1, 0, // +x +y -z 1
			10, 1, 10, // +x +y +z 2
			0, 1, 10, // -x +y +z 3
			
			0, 0, 0, // -x -y -z 4
			10, 0, 0, // +x -y -z 5
			10, 0, 10, // +x -y +z 6
			0, 0, 10, // -x -y +z 7
		};
		
		int[] indices = {
			0, 3, 1, 1, 3, 2, // Up
			1, 2, 6, 1, 6, 5, // Right
			2, 3, 7, 2, 7, 6, // Front
			0, 7, 3, 0, 4, 7, // Left
			0, 1, 4, 1, 5, 4, // Back
			4, 5, 7, 4, 6, 7  // Bottom
		};
		
		float[] normals = calculateNormals(positions, indices);
		
		return loader.loadMesh(positions, normals, indices);
	}
	
	private static float[] calculateNormals(float[] positions, int[] indices) {
		float[] normals = new float[positions.length];
		for (int i = 0; i < positions.length / 3; i++) {
			
			// Sum of all normals from the faces containing this position
			Vector3f sum = new Vector3f();
			for (int j = 0; j < indices.length; j += 3) {
				// If face contains vector
				if (j == i || j + 1 == i || j + 2 == i) {
					// Get face indices
					int[] faceIndices = {
						indices[j],
						indices[j + 1],
						indices[j + 2]
					};
					// Get face positions
					Vector3f v1 = new Vector3f(positions[faceIndices[0]], positions[faceIndices[0] + 1], positions[faceIndices[0] + 2]);
					Vector3f v2 = new Vector3f(positions[faceIndices[1]], positions[faceIndices[1] + 1], positions[faceIndices[1] + 2]);
					Vector3f v3 = new Vector3f(positions[faceIndices[2]], positions[faceIndices[2] + 1], positions[faceIndices[2] + 2]);
					// Cross two vectors
					Vector3f normal = v1.sub(v2).cross(v3.sub(v2));
					// Normalize and add to sum
					normal.normalize();
					sum.add(normal);
				}
			}
			sum.normalize();
			
			normals[i * 3] = sum.x;
			normals[i * 3 + 1] = sum.y;
			normals[i * 3 + 2] = sum.z;
		}
		return normals;
	}
}
