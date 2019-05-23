package org.rev317.min.api.methods;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.utils.Settings;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank {
	public static final int[] BANKERS = new int[] { 44, 45, 494, 495, 498, 499, 909, 958, 1036, 2271, 2354, 2355, 3824,
			5488, 5901, 4456, 4457, 4458, 4459, 5912, 5913, 6362, 6532, 6533, 6534, 6535, 7605, 8948, 9710, 14367 };
	public static final int[] BANKS = new int[] { 782, 2213, 2995, 5276, 6084, 10517, 11402, 11758, 12759, 14367, 19230,
			20325, 24914, 25808, 26972, 29085, 52589, 34752, 35647, 36786, 2012, 2015, 2019, 693, 4483, 12308, 20607,
			21301, 27663, 42192 };

	public static Item getItem(int id) {
		if (!isOpen()) {
			return null;
		}

		Item[] items;
		if ((items = Bank.getBankItems()) != null) {
			for (Item i : items) {
				if (i.getId() == id) {
					return i;
				}
			}
		}

		return null;
	}

	public static void withdraw(int id, int amount, int sleep) {
		if (!isOpen()) {
			return;
		}

		Item b = getItem(id);
		if (b == null) {
			return;
		}

		if (amount == 1) {
			b.transform(Items.Option.TRANSFORM_ONE, Settings.getActionByName("item_interface_id"));
		} else if (amount == 5) {
			b.transform(Items.Option.TRANSFORM_FIVE, Settings.getActionByName("item_interface_id"));
		} else if (amount == 10) {
			b.transform(Items.Option.TRANSFORM_TEN, Settings.getActionByName("item_interface_id"));
		} else if (amount == 0) {
			b.transform(Items.Option.TRANSFORM_ALL, Settings.getActionByName("item_interface_id"));
		} else {
			b.transform(Items.Option.TRANSFORM_X, Settings.getActionByName("item_interface_id"));
			Time.sleep(1500 + sleep);
			Keyboard.getInstance().sendKeys("" + amount);
		}
	}

	public static SceneObject[] getNearestBanks() {
		return SceneObjects.getNearest(BANKS);
	}

	public static int[] getBankStacks() {
		if (Bank.isOpen())
			return Loader.getClient().getInterfaceCache()[Settings.getActionByName("item_interface_id")]
					.getStackSizes();
		return null;
	}

	public static void depositAllExcept(int... exceptions) {
		if (Bank.isOpen()) {
			final ArrayList<Integer> ignored = new ArrayList<>();
			for (int i : exceptions) {
				ignored.add(i);
			}

			for (Item i : Inventory.getItems()) {
				if (!ignored.contains(i.getId())) {
					while (Bank.isOpen() && Inventory.getCount(i.getId()) > 0) {
						i.transform(Items.Option.TRANSFORM_ALL, Settings.getActionByName("inventory_parent_id"));
						ignored.add(i.getId());
						final int previous = Inventory.getCount(true);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return Inventory.getCount(true) != previous;
							}
						}, 500);
					}
				}
			}
		}
	}

	public static int getBankCount() {
		if (!Bank.isOpen()) {
			return 0;
		}
		int[] a = Bank.getBankItemIDs();
		if (a == null)
			return 0;
		return a.length;
	}

	public static Npc getBanker() {
		return Npcs.getClosest(BANKERS);
	}

	public static int getCount(int id) {
		if (!isOpen()) {
			return 0;
		}
		Item item;

		return ((item = getItem(id)) != null ? item.getStackSize() : 0);
	}

	public static Item[] getBankItems() {
		if (!isOpen()) {
			return null;
		}

		ArrayList<Item> items = new ArrayList<>();
		int[] ids = getBankItemIDs();
		int[] stacks = getBankStacks();
		if (ids != null && stacks != null) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] > 0) {
					items.add(new Item(ids[i], stacks[i], i));
				}
			}
		}

		return items.toArray(new Item[items.size()]);
	}

	public static boolean open() {
		if (isOpen()) {
			return false;
		}

		SceneObject bank = getBank();
		Npc banker = getBanker();

		if (bank != null) {
			bank.interact(SceneObjects.Option.USE);
			return true;
		} else if (banker != null) {
			banker.interact(Npcs.Option.BANK);
			return true;
		}

		return false;
	}

	public static void open(final SceneObject bank) {
		if (isOpen()) {
			return;
		}
		if (bank.getLocation().distanceTo() > 8) {
			bank.getLocation().walkTo();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return bank.distanceTo() < 8;
				}
			}, 5000);
			return;
		}

		bank.interact(SceneObjects.Option.USE);
	}

	public static void close() {
		if (!Bank.isOpen()) {
			return;
		}
		Menu.sendAction(200, -1, -1, Settings.getActionByName("button_close_bank"));
	}

	public static boolean isOpen() {
		if (Loader.getClient().getOpenInterfaceId() != Settings.getActionByName("bank_interface_id"))
			return false;
		return true;
	}

	public static SceneObject getBank() {
		SceneObject[] banks = getNearestBanks();
		if (banks != null && banks[0] != null) {
			return banks[0];
		}
		return null;
	}

	public static void depositAll() {
		Menu.clickButton(Settings.getActionByName("button_deposit_all"));
	}

	public static int[] getBankItemIDs() {
		if (!isOpen()) {
			return null;
		}
		return Loader.getClient().getInterfaceCache()[Settings.getActionByName("item_interface_id")].getItems();
	}
}
