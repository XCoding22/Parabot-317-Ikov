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

	public GameActionEvent(int f, int e, int d, int c, int b, int a) {
		this.action = f;
		this.cmd1 = e;
		this.cmd2 = d;
		this.cmd3 = c;
		this.cmd4 = b;
		this.index = a;
	}

	public int getIndex() {
		return index;
	}
}
