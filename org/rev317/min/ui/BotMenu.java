package org.rev317.min.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import org.parabot.core.Context;

public class BotMenu implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent a) {
		Context.getInstance().getPaintDebugger().toggle(a.getActionCommand());
	}

	public BotMenu(JMenuBar ba) {
	}

}
