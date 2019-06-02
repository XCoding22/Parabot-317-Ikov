package org.rev317.min.api.methods;

import org.rev317.min.Loader;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

public class Walking {

    public static void walkTo(Tile tile, Tile tile2) {
        Loader.getClient().walkTo(0, 0, 0, 0, '\u0000', tile.getRegionY(), 0, 0, tile2.getRegionY(), tile.getRegionX(), 1130997729, true, tile2.getRegionX(), (short)-23967);
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