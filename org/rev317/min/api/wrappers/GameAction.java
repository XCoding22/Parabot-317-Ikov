package org.rev317.min.api.wrappers;

import org.rev317.min.accessors.MenuActions;

public class GameAction {
	private MenuActions menuAction;

	public int getMenuActionId() {
		return menuAction.getMenuActionId();
	}

	public int getMenuAction4() {
		return menuAction.getMenuAction4();
	}

	public int getMenuAction1() {
		return menuAction.getMenuAction1();
	}

	public int getMenuAction3() {
		return menuAction.getMenuAction3();
	}

	public String getUnknown2() {
		return menuAction.getUnknown2();
	}

	public int getMenuAction2() {
		return menuAction.getMenuAction2();
	}

	public String getUnknown1() {
		return menuAction.getUnknown1();
	}

	public GameAction(MenuActions menuaction) {
		this.menuAction = menuaction;
	}
}
