package org.rev317.min.api.wrappers;

import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.interfaces.TileFlags;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;

public final class Tile implements TileFlags, Locatable {
	private int x;
	private int y;
	private int z;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final int getRegionX() {
		return x - Game.getBaseX();
	}

	public final int getRegionY() {
		return y - Game.getBaseY();
	}

	public final int getPlane() {
		return z;
	}

	@Override
	public final int distanceTo() {
		return (int) Calculations.distanceTo(this);
	}

	public final boolean isOnMinimap() {
		return distanceTo() < 16;
	}

	@Override
	public String toString() {
		return "Tile: [" + getX() + ", " + getY() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		final Tile t = (Tile) obj;
		return t.getX() == this.getX() && t.getY() == this.getY() && t.getPlane() == this.getPlane();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + this.x;
		hash = 31 * hash + this.y;
		hash = 31 * hash + this.z;

		return hash;
	}

	public void walkTo() {
		Walking.walkTo(Players.getMyPlayer().getLocation(), this);
	}

	public boolean isWalkable() {
		return (Game.getCollisionFlags()[getRegionX()][getRegionY()] & 256) == 0;
	}

	public boolean isReachable(boolean isObject) {
		Tile current = Players.getMyPlayer().getLocation();
		return Calculations.dijkstraDist(current.getRegionX(), current.getRegionY(), getRegionX(), getRegionY(),
				isObjectTile()) > -1;
	}

	public boolean isReachable() {
		return isReachable(isObjectTile());
	}

	public boolean isObjectTile() {
		return (Game.getCollisionFlags()[getRegionX()][getRegionY()] & OBJECT_TILE) != 0;
	}

	@Override
	public Tile getLocation() {
		return this;
	}
}