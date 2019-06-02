package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;

public class Game {
    public static void login(String string, String string2, boolean bl) {
        Loader.getClient().login(string, string2, bl);
    }

    public static int[] getSettings() {
        return Loader.getClient().getSettings();
    }

    public static void dropClient() {
        Loader.getClient().dropClient();
    }

    public static void login(String string, String string2) {
        Game.login((String)string, (String)string2, (boolean)false);
    }

    public static int getOpenBackDialogId() {
        return Loader.getClient().getBackDialogId();
    }

    public static int getLoopCycle() {
        return Loader.getClient().getLoopCycle();
    }

    public static int[][] getCollisionFlags() {
        return Loader.getClient().getCollisionMap()[Game.getPlane()].getFlags();
    }

    public static int getOpenInterfaceId() {
        return Loader.getClient().getOpenInterfaceId();
    }

    public static boolean isLoggedIn() {
        return Loader.getClient().isLoggedIn();
    }

    public static int getSetting(int n) {
        return Loader.getClient().getSettings()[n];
    }

    public static boolean confirmedDropClient() {
		Loader.getClient().dropClient();
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return !isLoggedIn();
			}
		}, 2500);
		return !isLoggedIn();
	}

    public static int getBaseX() {
        return Loader.getClient().getBaseX();
    }

    public static int getPlane() {
        return Loader.getClient().getPlane();
    }

    public static long[] getFriendsListAsLong() {
        return Loader.getClient().getFriendsListAsLong();
    }

    public static int getBaseY() {
        return Loader.getClient().getBaseY();
    }

    public static boolean hasAction4() {
        return true;
    }

}