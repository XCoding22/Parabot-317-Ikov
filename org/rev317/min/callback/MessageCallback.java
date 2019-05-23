package org.rev317.min.callback;

import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.script.ScriptEngine;

public class MessageCallback {
	public static final void messageListenerHook(int type, String name, String message) {
		ScriptEngine.getInstance().dispatch(new MessageEvent(type, name, message));
	}
}
