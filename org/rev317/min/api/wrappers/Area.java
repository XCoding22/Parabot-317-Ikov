package org.rev317.min.api.wrappers;

import java.awt.Polygon;
import java.util.ArrayList;

public class Area {
	private Polygon p;

	public Area(Tile... tiles) {
		this.p = new Polygon();
		for (Tile tile : tiles) {
			p.addPoint(tile.getX(), tile.getY());
		}
	}

	public void addTile(Tile t) {
		p.addPoint(t.getX(), t.getY());
	}

	public Tile[] getPoints() {
		Tile[] tiles = new Tile[p.npoints];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile(p.xpoints[i], p.ypoints[i]);
		}

		return tiles;
	}

	public Tile[] getTiles() {
		int lowestX = -1;
		int lowestY = -1;
		int highestX = -1;
		int highestY = -1;
		ArrayList<Tile> t = new ArrayList<>();
		for (int i : p.xpoints) {
			if (i < lowestX || lowestX == -1) {
				lowestX = i;
			}
			if (i > highestX || highestX == -1) {
				highestX = i;
			}
		}
		for (int i : p.ypoints) {
			if (i < lowestY || lowestY == -1) {
				lowestY = i;
			}
			if (i > highestY || highestY == -1) {
				highestY = i;
			}
		}
		for (int x = lowestX - 1; x < highestX + 1; x++) {
			for (int y = lowestY - 1; y < highestY + 1; y++) {
				if (this.contains(x, y)) {
					t.add(new Tile(x, y));
				}
			}
		}

		return t.toArray(new Tile[t.size()]);
	}

	public boolean contains(Tile tile) {
		return this.contains(tile.getX(), tile.getY());
	}

	public boolean contains(int x, int y) {
		int i;
		int j;
		boolean result = false;
		for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
			if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1)
					&& (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i]) / (p.ypoints[j] - p.ypoints[i])
							+ p.xpoints[i])) {
				result = !result;
			}
		}

		return result;
	}
}