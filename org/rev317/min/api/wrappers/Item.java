package org.rev317.min.api.wrappers;

import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;

public class Item {
	private int id, stackSize;
	private int slot;

	public Item(int id, int stackSize, int slot) {
		this.id = id;
		this.stackSize = stackSize;
		this.slot = slot;
	}

	public int getId() {
		return id;
	}

	public int getStackSize() {
		return stackSize;
	}

	public int getSlot() {
		return slot;
	}

	public void drop() {
		Menu.drop(this);
	}

	public void interact(Items.Option option) {
		Menu.interact(this, option);
	}

	public void transform(Items.Option option, int interfaceParentId) {
		Menu.transformItem(this, option, interfaceParentId);
	}

	/**
	 * @deprecated
	 */
	public void interact(int i) {
		Menu.interact(this, i);
	}

	/**
	 * @deprecated
	 */
	public void interact(String s) {
		Menu.interact(this, s);
	}

	/**
	 * @deprecated
	 */
	public void transform(int actionIndex, int interfaceParentId) {
		Menu.transformItem(this, actionIndex, interfaceParentId);
	}
}