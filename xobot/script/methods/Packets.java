package xobot.script.methods;

import org.rev317.min.api.methods.Menu;

public class Packets {
	public static void sendAction(String g, String f, int e, int d, int c, int b, int a) {
		Menu.sendAction(e, d, c, b, a, 0);
	}
}
