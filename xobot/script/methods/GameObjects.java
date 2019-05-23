package xobot.script.methods;

import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import xobot.script.wrappers.interactive.GameObject;

public class GameObjects {

	public static GameObject getNearest(int... c) {
		SceneObject[] b = SceneObjects.getNearest(c);
		if (b != null && b.length > 0) {
			return new GameObject(b[0]);
		}
		return null;
	}
}
