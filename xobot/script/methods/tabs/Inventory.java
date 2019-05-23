package xobot.script.methods.tabs;

import org.rev317.min.api.wrappers.Item;

public class Inventory {

	public static boolean isFull() {
		return org.rev317.min.api.methods.Inventory.isFull();
	}

	public static boolean isEmpty() {
		return org.rev317.min.api.methods.Inventory.isEmpty();
	}

	public static xobot.script.wrappers.interactive.Item getItem(int b) {
		Item a = org.rev317.min.api.methods.Inventory.getItem(b);
		if (a != null) {
			return new xobot.script.wrappers.interactive.Item(a);
		}
		return null;
	}

	public static boolean Contains(int... a) {
		return org.rev317.min.api.methods.Inventory.contains(a);
	}

	public static int getCount(int... a) {
		return org.rev317.min.api.methods.Inventory.getCount(a);
	}
}
