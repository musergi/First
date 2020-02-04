package dev.musergi.first.engine.render.gameobject;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
	protected String name;
	protected List<Component> components;
	
	public GameObject(String name) {
		this.name = name;
		components = new ArrayList<Component>();
		addComponent(new Transform());
	}
	
	public GameObject() {
		this("GameObject");
	}
	
	public void update() {
		for (Component component: components) {
			component.update();
		}
	}
	
	public void addComponent(Component component) {
		components.add(component);
		component.setParent(this);
	}
	
	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component component: components) {
			if (component.getClass().isAssignableFrom(componentClass)) {
				return (T) component;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("GameObject(");
		sb.append(name);
		sb.append(", ");
		sb.append("[");
		String[] names = new String[components.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = components.get(i).getName();
		}
		sb.append(String.join(" ", names));
		sb.append("]");
		return sb.toString();
	}
}
