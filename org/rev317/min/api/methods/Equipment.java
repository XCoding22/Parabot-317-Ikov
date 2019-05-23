package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.wrappers.Item;

public class Equipment {
	public static void equip(final int id) {
		Item item = Inventory.getItem(id);
		if (item != null) {
			item.interact(Items.Option.WEAR);
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return isWearing(id);
				}
			}, 3000);
		}
	}

	public static void unequip(int id) {
		for (Slot slot : Slot.values()) {
			if (slot.id() == id) {
				unequip(slot);
			}
		}
	}

	public static void unequip(final Slot slot) {
		Menu.sendAction(632, slot.id() - 1, slot.slot, 1688, -1, 3);
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return slot.id() == 0;
			}
		}, 3000);
	}

	public static boolean isWearing(int id) {
		for (Slot slot : Slot.values()) {
			if (slot.id() == id) {
				return true;
			}
		}

		return false;
	}

	public static int getItemId(Slot slot) {
		return slot.id();
	}

	public static boolean hasItem(Slot slot) {
		return getItemId(slot) != 0;
	}

	public static boolean isEmpty(Slot slot) {
		return getItemId(slot) == 0;
	}

	public enum Slot {
		HEAD(0), CAPE(1), AMMULET(2), WEAPON(3), BODY(4), SHIELD(5), LEGS(7), GLOVES(9), BOOTS(10), RING(12), AMMO(13);

		private final int INTERFACE_ID = 1688;
		private int slot;

		Slot(int slot) {
			this.slot = slot;
		}

		public int id() {
			return Loader.getClient().getInterfaceCache()[INTERFACE_ID].getItems()[slot];
		}
	}
}