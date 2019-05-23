package org.rev317.min.api.methods;

import org.rev317.min.Loader;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

public class Walking {

	public static void walkTo(Tile from, Tile to) {
		Loader.getClient().walkTo(0, 0, 0, 0, from.getRegionY(), 0, (short) 0, (short) 26764, 0, to.getRegionY(),
				from.getRegionX(), true, to.getRegionX(), 630234110);
	}

	public static void walkTo(Tile to) {
		walkTo(Players.getMyPlayer().getLocation(), to);
	}

	public static boolean walkDown(TilePath tilePath) {
		if (tilePath.hasReached()) {
			return true;
		}

		tilePath.traverse();
		return false;
	}

	public static Tile getNearestTileTo(Tile tile) {
		Tile loc = Players.getMyPlayer().getLocation();
		for (int i = 0; i < 1000; ++i) {
			if (tile.distanceTo() < 16 && tile.isWalkable()) {
				return tile;
			}
			tile = new Tile((loc.getX() + tile.getX()) / 2, (loc.getY() + tile.getY()) / 2);
		}

		return null;
	}
}