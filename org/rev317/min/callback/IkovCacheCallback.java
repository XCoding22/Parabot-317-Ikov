package org.rev317.min.callback;

import org.rev317.min.Ikov;

public class IkovCacheCallback {

	public static void afterCache() {
		Ikov.avoidBan();
	}
}
