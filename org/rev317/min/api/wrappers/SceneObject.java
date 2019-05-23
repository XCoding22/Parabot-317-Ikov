package org.rev317.min.api.wrappers;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefMethod;
import org.rev317.min.accessors.SceneObjectTile;
import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;

public class SceneObject implements Locatable {
	public static final int TYPE_WALL = 0;
	public static final int TYPE_WALLDECORATION = 1;
	public static final int TYPE_GROUNDDECORATION = 2;
	public static final int TYPE_GROUNDITEM = 3;
	public static final int TYPE_INTERACTIVE = 4;

	public SceneObjectTile accessor;
	private int type;

	public SceneObject(SceneObjectTile accessor, int type) {
		this.accessor = accessor;
		this.type = type;
	}

	public final int getHash() {
		return accessor.getHash();
	}

	public final Object resolveHash() {
		Object hash = (int) 0;
		try {
			RefMethod hashMethod = new RefClass(
					Context.getInstance().getASMClassLoader().loadClass("org.rev317.min.accessors.SceneObjectTile"),
					accessor).getMethod("getHash");
			if (hashMethod.getReturnType() == int.class) {
				hash = (int) hashMethod.invoke();
			} else {
				hash = (long) hashMethod.invoke();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return hash;
	}

	public final Tile getLocation() {
		return new Tile(Game.getBaseX() + getLocalRegionX(), Game.getBaseY() + getLocalRegionY());
	}

	public final int getLocalRegionX() {
		return accessor.getHash() & 0x7f;
	}

	public final int getLocalRegionY() {
		return accessor.getHash() >> 7 & 0x7f;
	}

	public final int getId() {
		return accessor.getHash() >> 14 & 0x7FFF;
	}

	public final int getType() {
		return type;
	}

	public final int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}

	public void interact(SceneObjects.Option option) {
		Menu.interact(this, option);
	}

	/**
	 * @deprecated
	 */
	public void interact(int actionIndex) {
		Menu.interact(this, actionIndex);
	}

	public RefClass getRefClass() {
		return new RefClass(this.accessor);
	}

	@Override
	public String toString() {
		return String.format("[ID: %d, X: %d, Y: %d]", getId(), getLocalRegionX(), getLocalRegionY());
	}
}