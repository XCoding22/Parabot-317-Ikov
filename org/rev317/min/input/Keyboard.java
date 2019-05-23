package org.rev317.min.input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.parabot.environment.api.utils.Time;

public class Keyboard extends org.parabot.environment.input.Keyboard {
	private Component component;

	public void sendKeyEvent(KeyEvent f) {
		try {
			Method method = null;

			for (KeyListener listener : component.getKeyListeners()) {
				if (!(listener instanceof org.parabot.environment.input.Keyboard) && !f.isConsumed()) {
					Class<?> componentClass = component.getClass();
					switch (f.getID()) {
					case 400:
						method = componentClass.getDeclaredMethod("keyTyped", KeyEvent.class);
						break;
					case 401:
						method = componentClass.getDeclaredMethod("keyPressed", KeyEvent.class);
						break;
					case 402:
						method = componentClass.getDeclaredMethod("keyReleased", KeyEvent.class);
						break;
					}
				}
			}

			if (method != null) {
				method.invoke(component, f);
				Time.sleep(10);
			}
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException var6) {
			var6.printStackTrace();
		}

	}

	public Keyboard(Component a) {
		super(a);
		component = a;
	}
}
