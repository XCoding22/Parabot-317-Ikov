package xobot.script.methods.tabs;

import org.rev317.min.Loader;

public enum Skills {
	ATTACK("ATTACK", 0), DEFENSE("DEFENSE", 1), STRENGTH("STRENGTH", 2), HITPOINTS("HITPOINTS", 3), RANGE("RANGE",
			4), PRAYER("PRAYER", 5), MAGIC("MAGIC", 6), COOKING("COOKING", 7), WOODCUTTING("WOODCUTTING", 8), FLETCHING(
					"FLETCHING", 9), FISHING("FISHING", 10), FIREMAKING("FIREMAKING", 11), CRAFTING("CRAFTING",
							12), SMITHING("SMITHING", 13), MINING("MINING", 14), HERBLORE("HERBLORE",
									15), AGILITY("AGILITY", 16), THIEVING("THIEVING", 17), SLAYER("SLAYER",
											18), FARMING("FARMING", 19), RUNECRAFTING("RUNECRAFTING", 20), CONSTRUCTION(
													"CONSTRUCTION", 21), HUNTER("HUNTER", 22), SUMMONING("SUMMONING",
															23), DUNGEONEERING("DUNGEONEERING", 24);

	private static final int[] EXPERIENCE = { 0, 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107,
			2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833,
			16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983,
			75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742,
			302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895,
			1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594,
			3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629,
			11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069,
			31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828,
			85539082, 94442737, 104273167 };

	final int index;
	final String name;

	Skills(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public final int getCurrentExp() {
		return Skills.getCurrentExperience(ordinal());
	}

	public final String toString() {
		return new StringBuilder("Skill: [").append(getName()).append(": ").append(getLevel()).append(" / ")
				.append(getRealLevel()).append("]").toString();
	}

	public static final int getExperienceByLevel(int level) {
		if (level > 99 || level < 1) {
			return 0;
		}

		return EXPERIENCE[level];
	}

	public final int getExperience() {
		return Skills.getCurrentExperience(ordinal());
	}

	public final int getPercentage() {
		return Skills.getPercentToNextLevel(ordinal());
	}

	public int getIndex() {
		return ordinal();
	}

	public static final int getPercentToNextLevel(int index) {
		int currentLevel = getLevelByExperience(getCurrentExperience(index));
		int nextLevel = currentLevel + 1;
		if (currentLevel == 99 || nextLevel > 99 || currentLevel < 1 || nextLevel < 1) {
			return 0;
		}

		return (int) (100f * ((float) getCurrentExperience(index) / (float) EXPERIENCE[nextLevel]));
	}

	public final int getLevel() {
		return Skills.getCurrentLevel(ordinal());
	}

	public final String getName() {
		return Character.toUpperCase(name().charAt(0)) + name().toLowerCase().substring(1);
	}

	public final int getRemaining() {
		return Skills.getRemainingExperience(ordinal());
	}

	public static final int getCurrentExperience(int a) {
		return Loader.getClient().getCurrentExp()[a];
	}

	public final int getCurrentLevel() {
		return Skills.getCurrentLevel(ordinal());
	}

	public static final int getCurrentLevel(int a) {
		return Loader.getClient().getCurrentStats()[a];
	}

	public static final int getCurrentExp(int a) {
		return Skills.getCurrentExperience(a);
	}

	public static final int getLevelByExperience(int experience) {
		for (int i = EXPERIENCE.length - 1; i > 0; i--) {
			if (experience > EXPERIENCE[i]) {
				return i;
			}
		}

		return 1;
	}

	public final int getRealLevel() {
		return Skills.getLevelByExperience(Skills.getCurrentExperience(ordinal()));
	}

	public static final int getRemainingExperience(int index) {
		int level = getLevelByExperience(getCurrentExperience(index));
		if (level >= 99 || level < 1) {
			return 0;
		}

		return EXPERIENCE[(level + 1)] - getCurrentExperience(index);
	}
}
