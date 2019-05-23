package org.rev317.min.api.methods;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.accessors.Interface;
import org.rev317.min.api.methods.utils.Settings;
import org.rev317.min.api.wrappers.Item;

public class Trading {

	public static boolean isOpen(boolean first) {
		return Interfaces.getOpenInterfaceId() == (first ? Settings.getActionByName("first_trade_interface_id")
				: Settings.getActionByName("second_trade_interface_id"));
	}

	public static boolean isOpen() {
		return Interfaces.getOpenInterfaceId() == Settings.getActionByName("first_trade_interface_id")
				|| Interfaces.getOpenInterfaceId() == Settings.getActionByName("second_trade_interface_id");
	}

	public static void close() {
		Players.getMyPlayer().getLocation().walkTo();
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return !isOpen();
			}
		}, 2500);
	}

	public static Item[] getMyOffer() {
		ArrayList<Item> items = new ArrayList<>();
		int[] ids = getItemIDs(Settings.getActionByName("my_offer_interface_id"));
		int[] stacks = getItemStacks(Settings.getActionByName("my_offer_interface_id"));
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] > 0) {
				items.add(new Item(ids[i], stacks[i], i));
			}
		}
		return items.toArray(new Item[items.size()]);
	}

	public static Item[] getOpponentsOffer() {
		ArrayList<Item> items = new ArrayList<>();
		int[] ids = getItemIDs(Settings.getActionByName("opponent_offer_interface_id"));
		int[] stacks = getItemStacks(Settings.getActionByName("opponent_offer_interface_id"));
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] > 0) {
				items.add(new Item(ids[i], stacks[i], i));
			}
		}
		return items.toArray(new Item[items.size()]);
	}

	private static int[] getItemIDs(int interfaceID) {
		Interface i;
		if ((i = Interfaces.getInterfaces()[interfaceID]) != null) {
			int[] items;
			if ((items = i.getItems()) != null && items.length > 0) {
				return items;
			}
		}
		return new int[0];
	}

	private static int[] getItemStacks(int interfaceID) {
		Interface i;

		if ((i = Interfaces.getInterfaces()[interfaceID]) != null) {
			int[] stacks;
			if ((stacks = i.getStackSizes()) != null && stacks.length > 0) {
				return stacks;
			}
		}
		return new int[0];
	}

	public static void acceptOffer() {
		Menu.clickButton(3420);
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return Interfaces.getOpenInterfaceId() == Settings.getActionByName("second_trade_interface_id");
			}
		}, 2500);
	}

	public static void acceptTrade() {
		Menu.clickButton(3546);
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return !isOpen();
			}
		}, 2500);
	}
}