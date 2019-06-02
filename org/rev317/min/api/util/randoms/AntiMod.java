package org.rev317.min.api.util.randoms;

import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;

public class AntiMod
implements Random {
    private final String[] staffNames = new String[]{"david", "demo", "lucas", "reece", "fergus", "mamba", "supreme", "ivy", "lolfish", "julia", "pride", "aaron", "ace", "autumn", "crim", "dionysos", "duke", "harry", "h a r r y", "haris", "hell kid", "rage", "tilt", "view", "yogi", "10 hitpoints", "99s", "cream", "delete", "fuk", "nike elite", "resistt", "savings", "xsj"};

    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }

    public String getServer() {
        return "Ikov";
    }

    private boolean hasStaff() {
        try {
            for(Player player : Players.getPlayers()) {
                for (String string : this.staffNames) {
                    if (player.getName().equalsIgnoreCase(string)) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (Exception exception) {
            System.out.println("Error in staff name check: " + exception.getMessage()); //what kind of error are you expecting to get?
        }
        return false;
    }

    public void execute() {
        Logger.addMessage((String)"There is staff nearby, logging out!");
        try {
            if (!Game.isLoggedIn()) return;
            Menu.sendAction(315, 0, 0, 2458, 0);
            Time.sleep(() -> !Game.isLoggedIn(), 10000);
            if (Game.isLoggedIn()) return;
            Context.getInstance().getRunningScript().setState(2);
            return;
        }
        catch (Exception exception) {
            System.out.println("Error in AntiMod: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public String getName() {
        return "Anti Moderator";
    }

    public boolean activate() {
        return this.hasStaff();
    }
}
