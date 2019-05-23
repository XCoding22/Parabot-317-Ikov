package org.rev317.min.debug;

import java.awt.Graphics;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.api.methods.Interfaces;

public class DInterfaces extends AbstractDebugger {
	private boolean enabled;

	@Override
	public void paint(Graphics g) {
		PaintDebugger p = Context.getInstance().getPaintDebugger();
		p.addLine("Open interface: " + Interfaces.getOpenInterfaceId());
		p.addLine("Open back dialog: " + Interfaces.getBackDialogId());
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void toggle() {
		enabled = !enabled;

		if (enabled) {
			System.out.println("Open interface: " + Interfaces.getOpenInterfaceId());
			System.out.println("Open back dialog: " + Interfaces.getBackDialogId());
		}
	}
}
