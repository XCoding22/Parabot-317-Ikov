package xobot.script.wrappers.interactive;

import org.rev317.min.Loader;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;
import org.rev317.min.api.wrappers.Tile;

public class NPC {
	private org.rev317.min.api.wrappers.Npc npc;

	public int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}

	public Tile getLocation() {
		return new Tile(Game.getBaseX() + getX(), Game.getBaseY() + getY(), Game.getPlane());
	}

	public int getAnimation() {
		return npc.getAccessor().getAnimation();
	}

	public final int getLoopCycleStatus() {
		return npc.getAccessor().getLoopCycleStatus();
	}

	public int getX() {
		return npc.getAccessor().getX() >> 7;
	}

	public final org.rev317.min.api.wrappers.Character getInteractingCharacter() {
		int b = npc.getAccessor().getInteractingEntity();
		if (b != -1 && b < 32768) {
			return new org.rev317.min.api.wrappers.Npc(Loader.getClient().getNpcs()[b], b);
		}
		if (b < 32768)
			return null;
		b -= 32768;
		try {
			if (Loader.getClient().getPlayers()[b] != null)
				return new Player(Loader.getClient().getPlayers()[b], b);
			return Players.getMyPlayer();
		} catch (Throwable a) {
			return Players.getMyPlayer();
		}
	}

	public int getY() {
		return npc.getAccessor().getY() >> 7;
	}

	public int getIndex() {
		return npc.getIndex();
	}

	public final int getMaxHealth() {
		return npc.getAccessor().getMaxHealth();
	}

	public void interact(String a) {
		Menu.interact(npc, Npcs.Option.FIRST);
	}

	public NPC(org.rev317.min.api.wrappers.Npc a) {
		npc = a;
	}

	public void interact(int a) {
		Menu.interact(npc, a);
	}

	public final int getHealth() {
		return npc.getAccessor().getCurrentHealth();
	}

	public boolean isInCombat() {
		return npc.getAccessor().getLoopCycleStatus() > Loader.getClient().getLoopCycle();
	}
}
