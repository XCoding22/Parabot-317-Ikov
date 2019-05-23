package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;
import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Menu;

public class GroundItem implements Locatable {
	private org.rev317.min.accessors.Item accessor;
	private int x;
	private int y;

	public GroundItem(org.rev317.min.accessors.Item accessor, int x, int y) {
		this.accessor = accessor;
		this.x = x;
		this.y = y;
	}

	public Tile getLocation() {
		return new Tile(Game.getBaseX() + x, Game.getBaseY() + y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void interact(GroundItems.Option option) {
		Menu.interact(this, option);
	}

	/**
	 * @deprecated
	 */
	public void interact(int actionIndex) {
		Menu.interact(this, actionIndex);
	}

	public void take() {
		Menu.take(this);
	}

	public int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}

	public int getId() {
		return accessor.getId();
	}

	public RefClass getRefClass() {
		return new RefClass(this.accessor);
	}
}