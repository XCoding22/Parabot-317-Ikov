package xobot.script.methods;

import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

import xobot.script.wrappers.interactive.NPC;

public class NPCs {
	public static NPC getNearest(int... c) {
		Npc[] b = Npcs.getNearest(c);
		if (b != null && b.length > 0) {
			return new NPC(b[0]);
		}
		return null;
	}
}
