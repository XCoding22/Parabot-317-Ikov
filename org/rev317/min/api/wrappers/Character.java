package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.utils.Emote;

public class Character implements Locatable {

	private org.rev317.min.accessors.Character accessor;
	private int index;

	public Character(org.rev317.min.accessors.Character accessor, int index) {
		this.accessor = accessor;
		this.index = index;
	}

	public int getX() {
		return accessor.getX() >> 7;
	}

	public int getY() {
		return accessor.getY() >> 7;
	}

	public int getIndex() {
		return this.index;
	}

	public int getAnimation() {
		return accessor.getAnimation();
	}

	public Tile getLocation() {
		return new Tile(Game.getBaseX() + getX(), Game.getBaseY() + getY(), Game.getPlane());
	}

	public int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}

	public final int getHealth() {
		return this.accessor.getCurrentHealth();
	}

	public final int getMaxHealth() {
		return this.accessor.getMaxHealth();
	}

	public final int getLoopCycleStatus() {
		return this.accessor.getLoopCycleStatus();
	}

	public boolean isInCombat() {
		return accessor.getLoopCycleStatus() > Loader.getClient().getLoopCycle();
	}

	/**
	 * @deprecated
	 */
	public void interact(int i) {
		Menu.interact(this, i);
	}

	public final Character getInteractingCharacter() {
		int index = this.accessor.getInteractingEntity();
		if (index != -1 && index < 32768) {
			return new Npc(Loader.getClient().getNpcs()[index], index);
		} else if (index >= 32768) {
			index -= 32768;
			try {
				if (Loader.getClient().getPlayers()[index] == null) {
					return Players.getMyPlayer();
				}
				return new Player(Loader.getClient().getPlayers()[index], index);
			} catch (Throwable t) {
				return Players.getMyPlayer();
			}
		}

		return null;
	}

	public void performEmote(Emote emote) {
		Menu.clickButton(emote.getAction3());
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return Players.getMyPlayer().getAnimation() != -1;
			}
		}, 1500);
	}

	public RefClass getRefClass() {
		return new RefClass(this.accessor);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessor == null) ? 0 : accessor.hashCode());
		result = prime * result + index;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Character other = (Character) obj;
		if (accessor == null) {
			if (other.accessor != null) {
				return false;
			}
		} else if (!accessor.equals(other.accessor)) {
			return false;
		}

		return index == other.index;
	}

	public org.rev317.min.accessors.Character getAccessor() {
		return accessor;
	}
}