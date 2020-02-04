package dev.musergi.first.engine.render.gameobject;

public abstract class Component {
	protected String name;
	protected GameObject parent;
	
	public Component(String name) {
		this.name = name;
	}
	
	public void update() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setParent(GameObject gameObject) {
		parent = gameObject;
	}
}
