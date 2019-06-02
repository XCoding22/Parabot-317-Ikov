package org.rev317.min.api.events;

public final class GameActionEvent {
	private int index;
	private int cmd3;
	private int action;
	private int cmd4;
	private int cmd1;
	private int cmd2;

	public int getCmd4() {
		return cmd4;
	}

	public int getCmd3() {
		return cmd3;
	}

	public int getAction() {
		return action;
	}

	public int getCmd2() {
		return cmd2;
	}

	public int getCmd1() {
		return cmd1;
	}

    public GameActionEvent(int n, int n2, int n3, int n4, int n5, int n6) {
        this.action = n;
        this.cmd1 = n2;
        this.cmd2 = n3;
        this.cmd3 = n4;
        this.cmd4 = n5;
        this.index = n6;
    }

	public int getIndex() {
		return index;
	}
}
