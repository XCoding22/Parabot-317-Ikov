package xobot.script.methods;

public class Bank {
	public static void depositAll() {
		org.rev317.min.api.methods.Bank.depositAll();
	}

	public static boolean isOpen() {
		return org.rev317.min.api.methods.Bank.isOpen();
	}
}
