package org.rev317.min.api.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.parabot.environment.api.utils.Filter;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.accessors.Deque;
import org.rev317.min.accessors.Node;
import org.rev317.min.api.methods.utils.Settings;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Tile;

public class GroundItems {
	private static final Comparator<GroundItem> NEAREST_SORTER = new Comparator<GroundItem>() {
		@Override
		public int compare(GroundItem n1, GroundItem n2) {
			return n1.distanceTo() - n2.distanceTo();
		}
	};
	private static final Filter<GroundItem> ALL_FILTER = new Filter<GroundItem>() {

		@Override
		public boolean accept(GroundItem item) {
			return true;
		}

	};
	private static Client client;

	public final static GroundItem[] getGroundItems(final Filter<GroundItem> filter) {
		if (client == null) {
			client = Loader.getClient();
		}
		final ArrayList<GroundItem> items = new ArrayList<>();
		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				final GroundItem[] groundItemsAtTile = getGroundItemsAt(x, y);
				if (groundItemsAtTile != null) {
					for (final GroundItem item : groundItemsAtTile) {
						if (filter.accept(item)) {
							items.add(item);
						}
					}
				}
			}
		}

		return items.toArray(new GroundItem[items.size()]);
	}

	public static final GroundItem[] getGroundItemsAt(final int x, final int y) {
		try {
			if (client == null) {
				client = Loader.getClient();
			}

			final Deque deque = client.getGroundItems()[Game.getPlane()][x][y];
			if (deque == null) {
				return null;
			}

			ArrayList<GroundItem> list = new ArrayList<>();
			final Node holder = deque.getHead();
			Node curNode = holder.getNext();
			while (curNode != null && curNode != holder && curNode != deque.getHead()) {
				final org.rev317.min.accessors.Item groundItem = (org.rev317.min.accessors.Item) curNode;
				list.add(new GroundItem(groundItem, x, y));
				curNode = curNode.getNext();
			}

			return list.toArray(new GroundItem[list.size()]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static final GroundItem[] getGroundItemsAt(final Tile tile) {
		return getGroundItemsAt(tile.getX(), tile.getY());
	}

	public static final GroundItem[] getGroundItems() {
		return getGroundItems(ALL_FILTER);
	}

	public static final GroundItem getClosest(final Filter<GroundItem> filter) {
		GroundItem[] objects = getNearest(filter);
		if (objects == null || objects.length == 0) {
			return null;
		}

		return objects[0];
	}

	public static final GroundItem getClosest(int... ids) {
		GroundItem[] objects = getNearest(ids);
		if (objects == null || objects.length == 0) {
			return null;
		}

		return objects[0];
	}

	public static final GroundItem[] getNearest(Filter<GroundItem> filter) {
		final GroundItem[] objects = getGroundItems(filter);
		Arrays.sort(objects, NEAREST_SORTER);

		return objects;
	}

	public static final GroundItem[] getNearest() {
		return getNearest(ALL_FILTER);
	}

	public static final GroundItem[] getNearest(final int... ids) {
		return getNearest(new Filter<GroundItem>() {
			@Override
			public boolean accept(GroundItem object) {
				for (final int id : ids) {
					if (id == object.getId()) {
						return true;
					}
				}

				return false;
			}
		});
	}

	public enum Option {
		FIRST(Settings.getActionByName("menu_ground_item_first_interaction")),

		SECOND(Settings.getActionByName("menu_ground_item_second_interaction")),

		THIRD(Settings.getActionByName("menu_ground_item_third_interaction")), TAKE(
				Settings.getActionByName("menu_ground_item_third_interaction")),

		FOURTH(Settings.getActionByName("menu_ground_item_fourth_interaction")),

		FIFTH(Settings.getActionByName("menu_ground_item_fifth_interaction")),

		EXAMINE(Settings.getActionByName("menu_ground_item_examine_interaction"));

		private int actionId;

		Option(int actionId) {
			this.actionId = actionId;
		}

		public int getActionId() {
			return actionId;
		}
	}
}