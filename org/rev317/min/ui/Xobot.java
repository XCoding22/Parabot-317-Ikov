package org.rev317.min.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.ethan.xobot.core.XobotLoader;
import org.parabot.core.ui.BotUI;
import org.parabot.core.ui.Logger;

public class Xobot implements ActionListener {
	private XobotLoader xobotLoader;
	private static JMenuItem xobot_menu;

	public Xobot() {
		xobotLoader = new XobotLoader();
	}

	public static void addXobotMenu() {
		xobot_menu = new JMenuItem("Load Xobot Scripts");
		xobot_menu.addActionListener(new Xobot());
		BotUI.getInstance().getScripts().add(xobot_menu);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (!Logger.getInstance().isVisible()) {
			BotUI.getInstance().performCommand("Logger");
		}
		new Thread() {
			@Override
			public void run() {
				xobot_menu.setEnabled(false);
				Logger.addMessage("Loading Xobot scripts");
				Xobot.this.xobotLoader.downloadScripts();
				xobot_menu.setEnabled(true);
			}
		}.start();
	}

}
