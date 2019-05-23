package org.ethan.xobot.core;

import java.util.Map;

import org.ethan.xobot.data.Server;
import org.ethan.xobot.handlers.ScriptLoader;

public class XobotLoader {

	public XobotLoader() {
	}

	private Map<Integer, String> a(Server a) {
		return new ScriptLoader().scriptMap("jketelaar", "auditt01", a);
	}

	public void downloadScripts() {
	}
}
