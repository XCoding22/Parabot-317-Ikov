package org.rev317.min.api.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.parabot.environment.api.utils.Filter;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.methods.utils.Settings;
import org.rev317.min.api.wrappers.Player;

public class Players {

	private static final Comparator<Player> NEAREST_SORTER = new Comparator<Player>() {

		@Override
		public int compare(Player p1, Player p2) {
			return p1.distanceTo() - p2.distanceTo();
		}

	};

	private static final Filter<Player> ALL_FILTER = new Filter<Player>() {

		@Override
		public boolean accept(Player p) {
			return true;
		}

	};

	public static final Player[] getPlayers(final Filter<Player> filter) {
		final Client client = Loader.getClient();
		ArrayList<Player> playerList = new ArrayList<>();
		final org.rev317.min.accessors.Player[] accPlayers = client.getPlayers();
		for (int i = 0; i < accPlayers.length; i++) {
			if (accPlayers[i] == null) {
				continue;
			}
			final Player player = new Player(accPlayers[i], i);
			if (filter.accept(player)) {
				playerList.add(player);
			}
		}

		return playerList.toArray(new Player[playerList.size()]);
	}

	public static final Player[] getPlayers() {
		return getPlayers(ALL_FILTER);
	}

	public static final Player[] getNearest(final Filter<Player> filter) {
		final Player[] players = getPlayers(filter);
		Arrays.sort(players, NEAREST_SORTER);
		return players;
	}

	public static final Player[] getNearest() {
		return getNearest(ALL_FILTER);
	}

	public static Player getMyPlayer() {
		return new Player(Loader.getClient().getMyPlayer(), -1);
	}

	public enum Option {
		FIRST(Settings.getActionByName("menu_character_first_interaction")),

		SECOND(Settings.getActionByName("menu_character_second_interaction")),

		THIRD(Settings.getActionByName("menu_character_third_interaction")),

		FOURTH(Settings.getActionByName("menu_character_fourth_interaction")),

		FIFTH(Settings.getActionByName("menu_character_fifth_interaction")),

		FOLLOW(Settings.getActionByName("menu_character_follow")),

		TRADE(Settings.getActionByName("menu_character_trade")),

		ATTACK(Settings.getActionByName("menu_character_attack")), CHALLENGE(
				Settings.getActionByName("menu_character_attack")),

		EXAMINE(Settings.getActionByName("menu_character_examine"));

		private int actionId;

		Option(int actionId) {
			this.actionId = actionId;
		}

		public int getActionId() {
			return actionId;
		}
	}
}