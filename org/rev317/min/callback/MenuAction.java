package org.rev317.min.callback;

import org.rev317.min.accessors.MenuActions;
import org.rev317.min.api.events.GameActionEvent;
import org.rev317.min.debug.DActions;
import org.rev317.min.script.ScriptEngine;

public class MenuAction {
	private static final String[][] outputs = {
			{ "[index: %d, action1: %d, action2: %d, action3: %d, action4: %d, id: %d]",
					"[id: %d, action1: %d, action2: %d, action3: %d, action4: %d, index: %d]" },
			{ "[index: %d, action1: %d, action2: %d, action3: %d, id: %d]",
					"[id: %d, action1: %d, action2: %d, action3: %d, index: %d]" } };

	public static String[][] getOutputs() {
		return outputs;
	}

	public static void intercept(MenuActions menuaction, char char1, int int1, char char2) {
		int f = menuaction.getMenuAction1();
		int e = menuaction.getMenuAction2();
		int d = menuaction.getMenuAction3();
		int c = menuaction.getMenuAction4();
		int b = menuaction.getMenuActionId();
		if (DActions.debugActions()) {
			StringBuilder builder = new StringBuilder("[id: ");
			builder = builder.append(b).append(", action1: ").append(f).append(", action2: ").append(e)
					.append(", action3: ").append(d).append(", ").append("action4: ").append(c).append(", string1: ")
					.append(menuaction.getUnknown1()).append(" string2: ").append(menuaction.getUnknown2())
					.append(", char1:  ").append(char1).append(" int1: ").append(int1).append(" char2: ").append(char2)
					.append("]");
			System.out.println(builder.toString());
		}
		ScriptEngine.getInstance().dispatch(new GameActionEvent(b, f, e, d, c, -1));
	}
}
