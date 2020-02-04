package dev.musergi.first.engine.render.gameobject;

import dev.musergi.first.engine.Application;
import dev.musergi.first.engine.render.mesh.Material;
import dev.musergi.first.engine.render.mesh.Mesh;
import dev.musergi.first.engine.render.renderer.Renderer;

public class MeshComponent extends Component {
	private Renderer renderer;
	private Mesh mesh;
	private Material material;

	public MeshComponent(Mesh mesh, Material material) {
		super("Mesh");
		this.mesh = mesh;
		this.material = material;
		renderer = Application.getRenderer();
	}

	public MeshComponent() {
		this(null, null);
	}

	@Override
	public void update() {
		if (mesh != null) {
			renderer.submitMesh(this, parent);
		}
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
