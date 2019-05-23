package xobot.script.wrappers.interactive;

import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class GameObject {
	private SceneObject object;

	public GameObject(SceneObject a) {
		object = a;
	}

	public void interact(String a) {
		Menu.interact(object, SceneObjects.Option.FIRST);
	}
}
