package org.rev317.min.api.methods;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.parabot.core.reflect.RefClass;
import org.rev317.min.IkovData;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.utils.Settings;
import org.rev317.min.api.wrappers.Character;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Player;
import org.rev317.min.api.wrappers.SceneObject;

public class Menu {

	public static void interact(SceneObject object, SceneObjects.Option action) {
		if (Game.hasAction4()) {
			sendAction(action.getActionId(), object.getHash(), object.getLocalRegionX(), object.getLocalRegionY(),
					object.getId(), 0);
		} else {
			sendAction(action.getActionId(), object.getHash(), object.getLocalRegionX(), object.getLocalRegionY());
		}
	}

	public static void interact(SceneObject object, int actionIndex) {
		int actionId = SceneObjects.Option.FIRST.getActionId();
		switch (actionIndex) {
		case 0:
			actionId = SceneObjects.Option.FIRST.getActionId();
			break;
		case 1:
			actionId = SceneObjects.Option.SECOND.getActionId();
			break;
		case 2:
			actionId = SceneObjects.Option.THIRD.getActionId();
			break;
		case 3:
			actionId = SceneObjects.Option.FOURTH.getActionId();
			break;
		case 4:
			actionId = SceneObjects.Option.FIFTH.getActionId();
			break;
		}
		if (Game.hasAction4()) {
			sendAction(actionId, object.getHash(), object.getLocalRegionX(), object.getLocalRegionY(), object.getId(),
					0);
		} else {
			sendAction(actionId, object.getHash(), object.getLocalRegionX(), object.getLocalRegionY());
		}
	}

	public static void interact(Npc character, Npcs.Option action) {
		sendAction(action.getActionId(), character.getIndex(), 0, 0);
	}

	public static void interact(Player character, Players.Option action) {
		sendAction(action.getActionId(), character.getIndex(), 0, 0);
	}

	public static void interact(Character character, int actionIndex) {
		int actionId = 20;
		switch (actionIndex) {
		case 0:
			actionId = 20;
			break;
		case 1:
			actionId = 412;
			break;
		case 2:
			actionId = 225;
			break;
		case 3:
			actionId = 965;
			break;
		case 4:
			actionId = 478;
			break;
		}

		sendAction(actionId, character.getIndex(), 0, 0);
	}

	public static void transformItem(Item item, Items.Option action, int interfaceParentId) {
		sendAction(action.getActionId(), item.getId() - 1, item.getSlot(), interfaceParentId);
	}

	public static void transformItem(Item item, int actionIndex, int interfaceParentId) {
		int actionId = Items.Option.TRANSFORM_FIRST.getActionId();
		switch (actionIndex) {
		case 0:
			actionId = Items.Option.TRANSFORM_FIRST.getActionId();
			break;
		case 1:
			actionId = Items.Option.TRANSFORM_SECOND.getActionId();
			break;
		case 2:
			actionId = Items.Option.TRANSFORM_THIRD.getActionId();
			break;
		case 3:
			actionId = Items.Option.TRANSFORM_FOURTH.getActionId();
			break;
		case 4:
			actionId = Items.Option.TRANSFORM_FIFTH.getActionId();
			break;
		}
		sendAction(actionId, item.getId() - 1, item.getSlot(), interfaceParentId);
	}

	public static void take(GroundItem item) {
		sendAction(Settings.getActionByName("button_take_item"), item.getId(), item.getX(), item.getY());
	}

	public static void interact(GroundItem item, GroundItems.Option action) {
		sendAction(action.getActionId(), item.getId(), item.getX(), item.getY());
	}

	public static void interact(GroundItem item, int action) {
		int actionId = GroundItems.Option.FIRST.getActionId();
		switch (action) {
		case 0:
			actionId = GroundItems.Option.FIRST.getActionId();
			break;
		case 1:
			actionId = GroundItems.Option.SECOND.getActionId();
			break;
		case 2:
			actionId = GroundItems.Option.THIRD.getActionId();
			break;
		case 3:
			actionId = GroundItems.Option.FOURTH.getActionId();
			break;
		case 4:
			actionId = GroundItems.Option.FIFTH.getActionId();
			break;
		}
		sendAction(actionId, item.getId(), item.getX(), item.getY());
	}

	public static void interact(Item item, Items.Option action) {
		sendAction(action.getActionId(), item.getId() - 1, item.getSlot(), 3214);
	}

	public static void interact(Item item, int action) {
		int actionId = 447;
		switch (action) {
		case 0:
			actionId = 447;
			break;
		case 1:
			actionId = 847;
			break;
		case 2:
			actionId = 1125;
			break;
		case 3:
			actionId = 1107;
			break;
		}
		sendAction(actionId, item.getId() - 1, item.getSlot(), 3214);
	}

	public static void interact(Item item, String action) {
		int actionId = 447;
		switch (action.toLowerCase()) {
		case "use":
			actionId = 447;
			break;
		case "drop":
			actionId = 847;
			break;
		case "examine":
			actionId = 1125;
			break;
		case "cancel":
			actionId = 1107;
			break;
		case "wear":
			actionId = 454;
			break;
		case "use with":
			actionId = 870;
			break;
		}
		sendAction(actionId, item.getId() - 1, item.getSlot(), 3214);
	}

	public static void drop(Item item) {
		sendAction(Settings.getActionByName("button_drop_item"), item.getId() - 1, item.getSlot(),
				Settings.getActionByName("inventory_index"));
	}

	public static void clickButton(int id) {
		sendAction(Settings.getActionByName("button_action_click"), 0, 0, id);
	}

	public static void sendAction(int action, int cmd1, int cmd2, int cmd3) {
		sendAction(action, cmd1, cmd2, cmd3, 1);
	}

	public static void sendAction(int action, int cmd1, int cmd2, int cmd3, int index) {
		sendAction(action, cmd1, cmd2, cmd3, 0, index);
	}

	public static void sendAction(int o, int n, int m, int l, int k, int j, String... i) {
		String h = "abc";
		String g = "def";
		if (i.length > 0) {
			h = i[0];
		}
		if (i.length > 1) {
			g = i[1];
		}

		try {
			Class<?> packetBuffer = Loader.getActionClass();

			Constructor<?> constructor = Loader.getActionClass().getConstructor(String.class, String.class,
					Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
			Object instance = constructor.newInstance(h, g, o, n, m, l, k);

			Class<?>[] methodsTypes = { java.lang.Character.TYPE, Integer.TYPE, java.lang.Character.TYPE,
					packetBuffer };
			new RefClass((Object) Loader.getClient()).getMethod(IkovData.getE(), methodsTypes).invoke((char) 0,
					322166898, (char) 40715, instance);
			return;
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException c) {
			c.printStackTrace();
			return;
		}
	}

}