package org.rev317.min.debug;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Filter;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class DSceneObjectsWallObj extends AbstractDebugger {

	private boolean enabled;

	@Override
	public void paint(Graphics g) {
		PaintDebugger p = Context.getInstance().getPaintDebugger();
		p.addLine("Wall objects: " + getWallObjects().length);
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void toggle() {
		enabled = !enabled;

		if (enabled) {
			SceneObject[] objects = getWallObjects();
			if (objects == null || objects.length == 0) {
				Logger.addMessage("There are no Wall Objects around you.");
				return;
			}
			java.util.List<SceneObject> objs = Arrays.asList(objects);
			Collections.sort(objs, DSceneObjects.SCENE_OBJECT_COMPARATOR_DISTANCE);
			objects = objs.toArray(new SceneObject[0]);

			for (int i = objects.length - 1; i >= 0; i--) {
				System.out.println(" ID: " + objects[i].getId() + " UID: " + objects[i].resolveHash() + " Location: "
						+ objects[i].getLocation() + " Distance: " + objects[i].distanceTo());
			}
		}
	}

	private SceneObject[] getWallObjects() {
		return SceneObjects.getNearest(new Filter<SceneObject>() {
			@Override
			public boolean accept(SceneObject sceneObject) {
				return sceneObject.getType() == SceneObject.TYPE_WALL;
			}
		});
	}
}
