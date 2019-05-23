package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;

public class Interfaces {

	private static final int optionAction = 2494;

	public static Interface[] getInterfaces() {
		return Loader.getClient().getInterfaceCache();
	}

	public static Interface getInterface(int id) {
		return getInterfaces()[id];
	}

	public static void openInterface(int id) {
		Loader.getClient().setInterface(id);
	}

	public static void setAmountOrNameInput(String text) {
		Loader.getClient().setAmountOrNameInput(text);
	}

	public static int getOpenInterfaceId() {
		return Loader.getClient().getOpenInterfaceId();
	}

	public static int getBackDialogId() {
		return Loader.getClient().getBackDialogId();
	}

	public static int getInputDialogState() {
		return Loader.getClient().getInputDialogState();
	}

	public static boolean isOpen(int id) {
		return getOpenInterfaceId() == id || getBackDialogId() == id;
	}

	public static boolean isOpen(int id, boolean backDialog) {
		if (backDialog) {
			return getBackDialogId() == id;
		} else {
			return getOpenInterfaceId() == id;
		}
	}

	public static void clickBackDialogOption(int index) {
		Menu.sendAction(315, 0, 0, optionAction + index, 0);
		Time.sleep(1000);
	}
}