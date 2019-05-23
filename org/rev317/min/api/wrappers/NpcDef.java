package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;

public class NpcDef {
	private org.rev317.min.accessors.NpcDef accessor;

	public NpcDef(org.rev317.min.accessors.NpcDef accessor) {
		this.accessor = accessor;
	}

	public int getId() {
		return getId(false);
	}

	private int getId(boolean avoidLong) {
		if (avoidLong) {
			return accessor.getId();
		} else {
			return accessor.getId();
		}
	}

	public RefClass getRefClass() {
		return new RefClass(this.accessor);
	}
}