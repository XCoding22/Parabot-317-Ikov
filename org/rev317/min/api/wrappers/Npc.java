package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;

public final class Npc extends Character {
	private org.rev317.min.accessors.Npc accessor;

	public Npc(org.rev317.min.accessors.Npc accessor, int index) {
		super(accessor, index);
		this.accessor = accessor;
	}

	public final NpcDef getDef() {
		return new NpcDef(this.accessor.getDef());
	}

	public RefClass getRefClass() {
		return new RefClass(this.accessor);
	}

	public String getName() {
		return getAccessor().getName();
	}

	public void interact(Npcs.Option option) {
		Menu.interact(this, option);
	}
}