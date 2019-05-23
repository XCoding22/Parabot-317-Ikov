package xobot.script.util;

import org.parabot.environment.scripts.framework.SleepCondition;

public class Time {
	public static void sleep(SleepCondition b, int a) {
		org.parabot.environment.api.utils.Time.sleep((SleepCondition) b, (int) a);
	}

	public static void sleep(int a) {
		org.parabot.environment.api.utils.Time.sleep((int) a);
	}
}
