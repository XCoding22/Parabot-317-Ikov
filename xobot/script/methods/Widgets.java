package xobot.script.methods;

import org.rev317.min.Loader;

public class Widgets {
	public static int getBackDialogId() {
		return Loader.getClient().getBackDialogId();
	}

	public static int getOpenInterface() {
		return Loader.getClient().getOpenInterfaceId();
	}
}
