package org.ethan.xobot.data;

public final class Server {
	public static final Server IKOV = new Server("Ikov", "C229B7ABEA4A2F6B4741980A74E72DEC");

	final String name, hash;

	private Server(String name, String hash) {
		this.name = name;
		this.hash = hash;
	}

	public String getServerHash() {
		return hash;
	}

	public String getName() {
		return name;
	}
}
