package org.rev317.min.api.wrappers;

public class TilePath {

	private Tile[] tiles;

	public TilePath(Tile[] tiles) {
		this.tiles = tiles;
	}

	public final Tile[] getTiles() {
		return tiles;
	}

	public final Tile getNextTile() {
		Tile next = null;
		for (Tile tile : tiles) {
			if (tile.isOnMinimap()) {
				next = tile;
			}
		}

		return next;
	}

	public final boolean isValid() {
		return getNextTile() != null;
	}

	public final boolean hasReached() {
		return tiles[tiles.length - 1].distanceTo() < 5;
	}

	public final void traverse() {
		final Tile next = getNextTile();
		if (next == null) {
			return;
		}

		next.walkTo();
	}

	public final TilePath reverse() {
		Tile[] newTiles = new Tile[tiles.length];
		for (int i = 0; i < tiles.length; i++) {
			newTiles[i] = tiles[tiles.length - i - 1];
		}

		return new TilePath(newTiles);
	}
}