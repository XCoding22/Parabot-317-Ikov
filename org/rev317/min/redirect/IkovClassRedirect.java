package org.rev317.min.redirect;

import java.lang.reflect.Method;

import org.parabot.core.Core;
import org.parabot.core.asm.RedirectClassAdapter;
import org.parabot.core.asm.redirect.ClassRedirect;
import org.parabot.environment.scripts.Script;
import org.rev317.min.Ikov;

public class IkovClassRedirect extends ClassRedirect {

	public static Method getDeclaredMethod(Class<?> e, String d, Class<?>... c)
			throws NoSuchMethodException, SecurityException {
		if (IkovClassRedirect.allowAccess() || IkovClassRedirect.allowClass(e)) {
			if (Ikov.shouldAvoidBan()) {
				return e.getDeclaredMethod(d, c);
			}
			if (e.getName().equals("d")) {
				try {
					Class<?> customUUIDClass = e.getClassLoader().loadClass("cc");
					Core.verbose("Got request for class: " + e.getName());
					return customUUIDClass.getDeclaredMethod(d, c);
				} catch (ClassNotFoundException b) {
					b.printStackTrace();
					return e.getDeclaredMethod(d, c);
				}
			}
		}

		System.err.println(e.getName() + "#" + e.getDeclaredMethod(d, c) + " Blocked.");
		throw RedirectClassAdapter.createSecurityException();
	}

	private static boolean allowAccess() {
		for (StackTraceElement element : new Exception().getStackTrace()) {
			if (element.getClassName().equals(Script.class.getName())) {
				return true;
			}
		}
		return false;
	}

	private static boolean allowClass(Class<?> a) {
		Core.verbose("Got request for class: " + a.getName());
		return a.getName().toLowerCase().contains("parabot");
	}
}
