package xobot.script.wrappers.interactive;

import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;

public class Item {
	private org.rev317.min.api.wrappers.Item item;

	public Item(org.rev317.min.api.wrappers.Item a) {
		item = a;
	}

	public void interact(String b) {
		int n;
		Items.Option[] arroption = Items.Option.values();
		for (Items.Option option : arroption) {
			if (option.toString().toLowerCase().equals(b)) {
				Menu.interact(item, option);
				return;
			}
		}
	}
}
