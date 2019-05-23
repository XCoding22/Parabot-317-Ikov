package xobot.script.methods;

import org.rev317.min.Loader;

public class Shop {
	public static void sell(int b, int a) {
		System.err.println("This method is not implemented");
	}

	public static void buy(int b, int a) {
		System.err.println("This method is not implemented");
	}

	public static boolean isOpen() {
		return Loader.getClient().getOpenInterfaceId() == 3824;
	}
}
