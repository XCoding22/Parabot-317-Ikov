package org.rev317.min.api.methods.utils;

public enum Settings {
	BANK_INTERFACE_ID(5292), ITEM_INTERFACE_ID(5382), BUTTON_CLOSE_BANK(5384), BUTTON_DEPOSIT_ALL(
			5386), BUTTON_ACTION_CLICK(646), BUTTON_DROP_ITEM(847), BUTTON_TAKE_ITEM(234), INVENTORY_PARENT_ID(
					5064), BANK_OPEN_ID(1), INVENTORY_INDEX(3214), MY_OFFER_INTERFACE_ID(
							3415), OPPONENT_OFFER_INTERFACE_ID(3416), FIRST_TRADE_INTERFACE_ID(
									3323), SECOND_TRADE_INTERFACE_ID(3443), MENU_SCENE_OBJECT_FIRST_INTERACTION(
											502), MENU_SCENE_OBJECT_SECOND_INTERACTION(
													900), MENU_SCENE_OBJECT_THIRD_INTERACTION(
															113), MENU_SCENE_OBJECT_FOURTH_INTERACTION(
																	872), MENU_SCENE_OBJECT_FIFTH_INTERACTION(
																			1062), MENU_SCENE_OBJECT_EXAMINE(
																					1226), MENU_CHARACTER_FOLLOW(
																							2779), MENU_CHARACTER_TRADE(
																									2027), MENU_CHARACTER_ATTACK(
																											2561), MENU_CHARACTER_FIRST_INTERACTION(
																													412), MENU_CHARACTER_SECOND_INTERACTION(
																															20), MENU_CHARACTER_THIRD_INTERACTION(
																																	225), MENU_CHARACTER_FOURTH_INTERACTION(
																																			965), MENU_CHARACTER_FIFTH_INTERACTION(
																																					478), MENU_CHARACTER_EXAMINE(
																																							1025), MENU_TRANSFORM_ONE_INTERACTION(
																																									632), MENU_TRANSFORM_FIVE_INTERACTION(
																																											78), MENU_TRANSFORM_TEN_INTERACTION(
																																													867), MENU_TRANSFORM_ALL_INTERACTION(
																																															431), MENU_TRANSFORM_X_INTERACTION(
																																																	53), MENU_TRANSFORM_ALL_BUT_ONE_INTERACTION(
																																																			775), MENU_TRANSFORM_EXAMINE_INTERACTION(
																																																					1125), MENU_GROUND_ITEM_FIRST_INTERACTION(
																																																							652), MENU_GROUND_ITEM_SECOND_INTERACTION(
																																																									567), MENU_GROUND_ITEM_THIRD_INTERACTION(
																																																											234), MENU_GROUND_ITEM_FOURTH_INTERACTION(
																																																													244), MENU_GROUND_ITEM_FIFTH_INTERACTION(
																																																															213), MENU_GROUND_ITEM_EXAMINE_INTERACTION(
																																																																	1448), MENU_ITEM_FIRST_INTERACTION(
																																																																			454), MENU_ITEM_SECOND_INTERACTION(
																																																																					74), MENU_ITEM_THIRD_INTERACTION(
																																																																							447), MENU_ITEM_FOURTH_INTERACTION(
																																																																									493), MENU_ITEM_FIFTH_INTERACTION(
																																																																											847), MENU_ITEM_SIXTH_INTERACTION(
																																																																													1125), MENU_ITEM_SEVENTH_INTERACTION(
																																																																															870);

	private int id;

	Settings(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static int getActionByName(String name) {
		for (Settings settings : Settings.values()) {
			if (name.equalsIgnoreCase(settings.toString())) {
				return settings.getId();
			}
		}

		return 0;
	}
}