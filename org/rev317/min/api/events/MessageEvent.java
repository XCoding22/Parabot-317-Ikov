package org.rev317.min.api.events;

public final class MessageEvent {
	public static final int TYPE_PLAYER = 2;
	private int k;
	public static final int TYPE_GENERIC = 0;
	private String j;
	private String PARABOT_IKOV;
	public static final int TYPE_TRADE = 4;
	public static final int TYPE_DUEL = 8;

	public final String getSender() {
		return PARABOT_IKOV;
	}

	public final String getMessage() {
		return j;
	}

	public final int getType() {
		return k;
	}

	public MessageEvent(int c, String b, String a) {
		this.k = c;
		this.PARABOT_IKOV = b;
		this.j = a;
	}
}
