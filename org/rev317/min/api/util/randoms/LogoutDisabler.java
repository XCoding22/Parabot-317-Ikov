package org.rev317.min.api.util.randoms;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.rev317.min.api.methods.Game;

public class LogoutDisabler implements Random {
	private final java.util.Random random;
	private final int[] keys = { 38, 40, 37, 39 };
	private long activateTime;

	public void execute() {
		int a = keys[random.nextInt(keys.length)];
		Keyboard.getInstance().pressKey(a);
		Time.sleep((random.nextInt(16) + 5));
		Keyboard.getInstance().releaseKey(a);
		activateTime = System.currentTimeMillis();
	}

	public LogoutDisabler() {
		this.random = new java.util.Random();
		this.activateTime = System.currentTimeMillis();
	}

	public boolean activate() {
		return !(!Game.isLoggedIn()
				|| (System.currentTimeMillis() - activateTime) / 1000L <= (long) (random.nextInt(21) + 30));
	}

	public String getServer() {
		return "Ikov";
	}

	public RandomType getRandomType() {
		return RandomType.SCRIPT;
	}

	public String getName() {
		return "Logout disabler";
	}
}
