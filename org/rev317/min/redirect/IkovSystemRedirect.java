package org.rev317.min.redirect;

import org.parabot.core.Core;
import org.parabot.core.Directories;

public class IkovSystemRedirect {

	private static String getClassPath() {
		return Directories.getDefaultDirectory().getAbsolutePath() + "/.ikov_cache/client.jar";
	}

	public static String getProperty(String s) {
		String value;
		switch (s) {
		case "java.class.path":
			value = getClassPath();
			break;
		default:
			value = System.getProperty(s);
			break;
		}

		Core.verbose(String.format("GetSystemProp %s = %s", s, value));
		return value;
	}

	public static String getProperty(String s, String s2) {
		String value = null;
		switch (s2) {
		case "java.class.path":
			value = getClassPath();
			break;
		}

		if (value == null) {
			switch (s) {
			case "java.class.path":
				value = getClassPath();
				break;
			default:
				value = System.getProperty(s);
				break;
			}
		}

		Core.verbose(String.format("GetSystemProp %s = %s", s, value));
		return value;
	}

}
