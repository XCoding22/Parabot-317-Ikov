package org.rev317.min.api.interfaces;

import org.rev317.min.api.wrappers.Tile;

public interface Locatable {
	public int distanceTo();

	public Tile getLocation();
}
