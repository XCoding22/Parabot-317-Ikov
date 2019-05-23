package org.rev317.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Skill;

public class DSkills extends AbstractDebugger {

	private boolean enabled;

	@Override
	public void toggle() {
		enabled = !enabled;
		if (enabled) {
			for (Skill skill : Skill.values()) {
				System.out.println(String.format("%s level: %d / %d (exp: %s)", skill.getName(), skill.getLevel(),
						skill.getRealLevel(), skill.getExperience()));
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void paint(Graphics graphics) {

	}
}
