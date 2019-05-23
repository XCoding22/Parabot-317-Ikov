package org.rev317.min.api.methods;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.parabot.core.Configuration;
import org.parabot.core.Context;
import org.parabot.environment.api.utils.WebUtil;
import org.rev317.min.api.methods.utils.Settings;

public class Items {

	private static JSONParser jsonParser = new JSONParser();

	private static HashMap<Integer, Long> prices = new HashMap<>();
	private static HashMap<Integer, String> names = new HashMap<>();

	public static String getName(int id) {
		String name;
		if ((name = names.get(id)) != null) {
			return name;
		} else {
			try {
				String content = WebUtil.getContents(Configuration.ITEM_API + id);
				if (content.length() > 0) {
					JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
					JSONObject itemInformation = (JSONObject) jsonObject.get("result");
					if (itemInformation.get("name") != null
							&& !((String) itemInformation.get("name")).equalsIgnoreCase("null")) {
						name = (String) itemInformation.get("name");
						names.put(id, name);
						return name;
					}
				}
			} catch (MalformedURLException | ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static int[] getIds(String name) {
		try {
			String content = WebUtil.getContents(Configuration.ITEM_API + name);
			if (content.length() > 0) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
				JSONArray array = (JSONArray) jsonObject.get("result");
				if (array.size() > 0) {
					int[] ids = new int[array.size()];
					for (int i = 0; i < array.size(); i++) {
						ids[i] = Integer.parseInt((String) array.get(i));
					}
					return ids;
				}
			}
		} catch (MalformedURLException | ParseException e) {
			e.printStackTrace();
		}

		return new int[0];
	}

	public static int[] getIdsStart(String name) {
		try {
			String content = WebUtil.getContents(Configuration.ITEM_API + "starts/" + name);
			if (content.length() > 0) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
				JSONArray array = (JSONArray) jsonObject.get("result");
				if (array.size() > 0) {
					int[] ids = new int[array.size()];
					for (int i = 0; i < array.size(); i++) {
						ids[i] = Integer.parseInt((String) array.get(i));
					}
					return ids;
				}
			}
		} catch (MalformedURLException | ParseException e) {
			e.printStackTrace();
		}

		return new int[0];
	}

	public static int[] getIdsContain(String name) {
		try {
			String content = WebUtil.getContents(Configuration.ITEM_API + "contains/" + name);
			if (content.length() > 0) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
				JSONArray array = (JSONArray) jsonObject.get("result");
				if (array.size() > 0) {
					int[] ids = new int[array.size()];
					for (int i = 0; i < array.size(); i++) {
						ids[i] = Integer.parseInt((String) array.get(i));
					}
					return ids;
				}
			}
		} catch (MalformedURLException | ParseException e) {
			e.printStackTrace();
		}

		return new int[0];
	}

	public static long getPrice(int id) {
		if (prices.containsKey(id)) {
			return prices.get(id);
		} else {
			try {
				String content = WebUtil.getContents(Configuration.ITEM_API + id + "/"
						+ Context.getInstance().getServerProviderInfo().getServerName());
				if (content.length() > 0) {
					JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
					JSONObject itemInformation = (JSONObject) jsonObject.get("result");
					if (itemInformation.get("price") != null
							&& !((String) itemInformation.get("price")).equalsIgnoreCase("null")) {
						long price = Long.parseLong((String) itemInformation.get("price"));
						prices.put(id, price);
						return price;
					}
				}
			} catch (MalformedURLException | ParseException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public enum Option {
		TRANSFORM_FIRST(Settings.getActionByName("menu_transform_one_interaction")), VALUE(
				Settings.getActionByName("menu_transform_one_interaction")), TRANSFORM_ONE(
						Settings.getActionByName("menu_transform_one_interaction")),

		TRANSFORM_SECOND(Settings.getActionByName("menu_transform_five_interaction")), TRANSFORM_FIVE(
				Settings.getActionByName("menu_transform_five_interaction")),

		TRANSFORM_THIRD(Settings.getActionByName("menu_transform_ten_interaction")), TRANSFORM_TEN(
				Settings.getActionByName("menu_transform_ten_interaction")),

		TRANSFORM_FOURTH(Settings.getActionByName("menu_transform_all_interaction")), TRANSFORM_ALL(
				Settings.getActionByName("menu_transform_all_interaction")),

		TRANSFORM_FIFTH(Settings.getActionByName("menu_transform_x_interaction")), TRANSFORM_X(
				Settings.getActionByName("menu_transform_x_interaction")), TRANSFORM_HUNDRED(
						Settings.getActionByName("menu_transform_x_interaction")),

		TRANSFORM_SIXTH(Settings.getActionByName("menu_transform_all_but_one_interaction")), TRANSFORM_ALL_BUT_ONE(
				Settings.getActionByName("menu_transform_all_but_one_interaction")),

		TRANSFORM_EXAMINE(Settings.getActionByName("menu_transform_examine_interaction")),

		FIRST(Settings.getActionByName("menu_item_first_interaction")), WEAR(
				Settings.getActionByName("menu_item_first_interaction")),

		SECOND(Settings.getActionByName("menu_item_second_interaction")), CONSUME(
				Settings.getActionByName("menu_item_second_interaction")), DRINK(
						Settings.getActionByName("menu_item_second_interaction")),

		THIRD(Settings.getActionByName("menu_item_third_interaction")), USE(
				Settings.getActionByName("menu_item_third_interaction")),

		FOURTH(Settings.getActionByName("menu_item_fourth_interaction")), EMPTY(
				Settings.getActionByName("menu_item_fourth_interaction")),

		FIFTH(Settings.getActionByName("menu_item_fifth_interaction")), DROP(
				Settings.getActionByName("menu_item_fifth_interaction")),

		SIXTH(Settings.getActionByName("menu_item_sixth_interaction")), EXAMINE(
				Settings.getActionByName("menu_item_sixth_interaction")),

		SEVENTH(Settings.getActionByName("menu_item_seventh_interaction")), USE_WITH(
				Settings.getActionByName("menu_item_seventh_interaction"));

		private int actionId;

		Option(int actionId) {
			this.actionId = actionId;
		}

		public int getActionId() {
			return actionId;
		}
	}
}