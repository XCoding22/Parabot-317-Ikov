package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.wrappers.Tile;

public class Calculations {

	public static final double distanceTo(Tile tile) {
		return distanceBetween(tile, Players.getMyPlayer().getLocation());
	}

	public static final double distanceBetween(Tile a, Tile b) {
		int x = b.getX() - a.getX();
		int y = b.getY() - a.getY();

		return Math.sqrt((x * x) + (y * y));
	}

	public static int dijkstraDist(final int startX, final int startY, final int destX, final int destY,
			final boolean findAdjacent) {
		try {
			final int[][] prev = new int[104][104];
			final int[][] dist = new int[104][104];
			final int[] path_x = new int[4000];
			final int[] path_y = new int[4000];
			for (int xx = 0; xx < 104; xx++) {
				for (int yy = 0; yy < 104; yy++) {
					prev[xx][yy] = 0;
					dist[xx][yy] = 99999999;
				}
			}
			int curr_x = startX;
			int curr_y = startY;
			prev[startX][startY] = 99;
			dist[startX][startY] = 0;
			int path_ptr = 0;
			int step_ptr = 0;
			path_x[path_ptr] = startX;
			path_y[path_ptr++] = startY;
			final int blocks[][] = Game.getCollisionFlags();
			final int pathLength = path_x.length;
			boolean foundPath = false;
			while (step_ptr != path_ptr) {
				curr_x = path_x[step_ptr];
				curr_y = path_y[step_ptr];
				if (Math.abs(curr_x - destX) + Math.abs(curr_y - destY) == (findAdjacent ? 1 : 0)) {
					foundPath = true;
					break;
				}
				step_ptr = (step_ptr + 1) % pathLength;
				final int cost = dist[curr_x][curr_y] + 1;

				if (curr_y > 0 && prev[curr_x][curr_y - 1] == 0 && (blocks[curr_x][curr_y - 1] & 0x1280102) == 0) {
					path_x[path_ptr] = curr_x;
					path_y[path_ptr] = curr_y - 1;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x][curr_y - 1] = 1;
					dist[curr_x][curr_y - 1] = cost;
				}

				if (curr_x > 0 && prev[curr_x - 1][curr_y] == 0 && (blocks[curr_x - 1][curr_y] & 0x1280108) == 0) {
					path_x[path_ptr] = curr_x - 1;
					path_y[path_ptr] = curr_y;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x - 1][curr_y] = 2;
					dist[curr_x - 1][curr_y] = cost;
				}

				if (curr_y < 104 - 1 && prev[curr_x][curr_y + 1] == 0
						&& (blocks[curr_x][curr_y + 1] & 0x1280120) == 0) {
					path_x[path_ptr] = curr_x;
					path_y[path_ptr] = curr_y + 1;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x][curr_y + 1] = 4;
					dist[curr_x][curr_y + 1] = cost;
				}

				if (curr_x < 104 - 1 && prev[curr_x + 1][curr_y] == 0
						&& (blocks[curr_x + 1][curr_y] & 0x1280180) == 0) {
					path_x[path_ptr] = curr_x + 1;
					path_y[path_ptr] = curr_y;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x + 1][curr_y] = 8;
					dist[curr_x + 1][curr_y] = cost;
				}

				if (curr_x > 0 && curr_y > 0 && prev[curr_x - 1][curr_y - 1] == 0
						&& (blocks[curr_x - 1][curr_y - 1] & 0x128010E) == 0
						&& (blocks[curr_x - 1][curr_y] & 0x1280108) == 0
						&& (blocks[curr_x][curr_y - 1] & 0x1280102) == 0) {
					path_x[path_ptr] = curr_x - 1;
					path_y[path_ptr] = curr_y - 1;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x - 1][curr_y - 1] = 3;
					dist[curr_x - 1][curr_y - 1] = cost;
				}

				if (curr_x > 0 && curr_y < 104 - 1 && prev[curr_x - 1][curr_y + 1] == 0
						&& (blocks[curr_x - 1][curr_y + 1] & 0x1280138) == 0
						&& (blocks[curr_x - 1][curr_y] & 0x1280108) == 0
						&& (blocks[curr_x][curr_y + 1] & 0x1280120) == 0) {
					path_x[path_ptr] = curr_x - 1;
					path_y[path_ptr] = curr_y + 1;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x - 1][curr_y + 1] = 6;
					dist[curr_x - 1][curr_y + 1] = cost;
				}

				if (curr_x < 104 - 1 && curr_y > 0 && prev[curr_x + 1][curr_y - 1] == 0
						&& (blocks[curr_x + 1][curr_y - 1] & 0x1280183) == 0
						&& (blocks[curr_x + 1][curr_y] & 0x1280180) == 0
						&& (blocks[curr_x][curr_y - 1] & 0x1280102) == 0) {
					path_x[path_ptr] = curr_x + 1;
					path_y[path_ptr] = curr_y - 1;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x + 1][curr_y - 1] = 9;
					dist[curr_x + 1][curr_y - 1] = cost;
				}

				if (curr_x < 104 - 1 && curr_y < 104 - 1 && prev[curr_x + 1][curr_y + 1] == 0
						&& (blocks[curr_x + 1][curr_y + 1] & 0x12801E0) == 0
						&& (blocks[curr_x + 1][curr_y] & 0x1280180) == 0
						&& (blocks[curr_x][curr_y + 1] & 0x1280120) == 0) {
					path_x[path_ptr] = curr_x + 1;
					path_y[path_ptr] = curr_y + 1;
					path_ptr = (path_ptr + 1) % pathLength;
					prev[curr_x + 1][curr_y + 1] = 12;
					dist[curr_x + 1][curr_y + 1] = cost;
				}
			}

			return foundPath ? dist[curr_x][curr_y] : -1;
		} catch (Exception e) {
			return -1;
		}
	}

	public static boolean foundPath(Tile from, Tile to) {
		return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), false) != -1;
	}

	public static boolean foundPath(Tile from, Tile to, boolean isObject) {
		return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), isObject) != -1;
	}

	public static int pathDistanceBetween(Tile from, Tile to) {
		if (foundPath(from, to)) {
			return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), false);
		}

		return -1;
	}

	public static int pathDistanceBetween(Tile from, Tile to, boolean isObject) {
		if (foundPath(from, to)) {
			return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), isObject);
		}

		return -1;
	}

	public static int pathDistanceTo(Tile tile) {
		return pathDistanceBetween(Players.getMyPlayer().getLocation(), tile);
	}

	public static int pathDistanceTo(Tile tile, boolean isObject) {
		return pathDistanceBetween(Players.getMyPlayer().getLocation(), tile, isObject);
	}

	public static boolean isSameTile(Tile first, Tile second) {
		return distanceBetween(first, second) == 0 && first.getPlane() == second.getPlane();
	}

	public static boolean atTile(Tile destination) {
		return isSameTile(Players.getMyPlayer().getLocation(), destination);
	}

	public static int gained(int start, int current) {
		return start - current;
	}

	public static int gainedPerHour(Timer runtime, int start, int current) {
		return runtime.getPerHour(gained(start, current));
	}
}