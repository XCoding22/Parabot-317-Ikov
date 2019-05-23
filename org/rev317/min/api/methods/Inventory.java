package org.rev317.min.api.methods;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;
import org.rev317.min.api.methods.utils.Settings;
import org.rev317.min.api.wrappers.Item;

public class Inventory {

	private static final Filter<Item> ALL_FILTER = new Filter<Item>() {

		@Override
		public boolean accept(Item i) {
			return true;
		}

	};

	public static void clear() {
		for (Item item : Inventory.getItems()) {
			item.drop();
			Time.sleep(60, 80);
		}
	}

	public static void clearExcept(int... ids) {
		for (Item item : getItems()) {
			for (int id : ids) {
				if (item.getId() != id) {
					item.drop();
				}
			}
		}
	}

	public static Interface getInterface() {
		return Loader.getClient().getInterfaceCache()[Settings.getActionByName("inventory_index")];
	}

	public static int getCount() {
		return getCount(false);
	}

	public static int getCount(int... ids) {
		return getCount(false, ids);
	}

	public static int getCount(final boolean includeStack) {
		final Interface inventory = getInterface();
		if (inventory == null) {
			return -1;
		}
		int count = 0;
		final int[] items = inventory.getItems();
		final int[] stackSizes = includeStack ? inventory.getStackSizes() : null;
		for (int i = 0; i < items.length; i++) {
			if (items[i] > 0) {
				count += includeStack ? stackSizes[i] : 1;
			}
		}
		return count;
	}

	public static int getCount(final boolean includeStack, int... ids) {
		final Interface inventory = getInterface();
		if (inventory == null) {
			return -1;
		}
		int count = 0;
		final int[] items = inventory.getItems();
		final int[] stackSizes = includeStack ? inventory.getStackSizes() : null;
		for (int i = 0; i < items.length; i++) {
			final int itemId = items[i];
			if (itemId > 0) {
				for (final int id : ids) {
					if (id == itemId) {
						count += includeStack ? stackSizes[i] : 1;
						break;
					}
				}
			}
		}
		return count;
	}

	public static Item[] getItems() {
		return getItems(ALL_FILTER);
	}

	public static Item[] getItems(final int... ids) {
		return getItems(new Filter<Item>() {

			@Override
			public boolean accept(Item e) {
				for (int id : ids) {
					if (e.getId() == id) {
						return true;
					}
				}
				return false;
			}

		});
	}

	public static Item[] getItems(final Filter<Item> filter) {
		final Interface inventory = getInterface();
		if (inventory == null) {
			return null;
		}
		final int[] items = inventory.getItems();
		final int[] stackSizes = inventory.getStackSizes();
		final ArrayList<Item> invItems = new ArrayList<>(28);
		for (int i = 0; i < items.length; i++) {
			final int itemId = items[i];
			if (itemId < 1) {
				continue;
			}
			final int stackSize = stackSizes[i];
			final Item item = new Item(itemId, stackSize, i);
			if (filter.accept(item)) {
				invItems.add(item);
			}
		}
		return invItems.toArray(new Item[invItems.size()]);
	}

	public static boolean isFull() {
		return Inventory.getCount() == 28;
	}

	public static boolean isEmpty() {
		return Inventory.getCount() == 0;
	}

	public static boolean contains(int... id) {
		return getCount(id) > 0;
	}

	@Deprecated
	public static boolean containts(int... id) {
		return contains(id);
	}

	public static Item getItem(int id) {
		for (Item i : getItems(id)) {
			if (i != null) {
				return i;
			}
		}
		return null;
	}

	public static boolean combine(int itemOne, int itemTwo) {
		Item io = getItem(itemOne);
		Item it = getItem(itemTwo);

		if (io != null) {
			if (it != null) {
				io.interact(Items.Option.USE);
				Time.sleep(50, 100);
				it.interact(Items.Option.USE_WITH);
				Time.sleep(50, 100);
				return true;
			}
		}
		return false;
	}

	public static boolean combine(int itemOne, int itemTwo, SleepCondition sleepCondition) {
		Item io = getItem(itemOne);
		Item it = getItem(itemTwo);

		if (io != null) {
			if (it != null) {
				io.interact(Items.Option.USE);
				Time.sleep(50, 100);
				it.interact(Items.Option.USE_WITH);
				Time.sleep(50, 100);
				sleepCondition.isValid();
				return true;
			}
		}
		return false;
	}
}