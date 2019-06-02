package org.rev317.min.api.events;


public final class MessageEvent {
    public static final int TYPE_PLAYER = 2;
    public static final int TYPE_DUEL = 8;
    public static final int TYPE_GENERIC = 0;
    public static final int TYPE_TRADE = 4;
    private int type;
    private String name;
    private String message;

    public final String getSender() {
        return this.name;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getType() {
        return this.type;
    }

    public MessageEvent(int n, String string, String string2) {
        this.type = n;
        this.name = string;
        this.message = string2;
    }
}
