package com.source.main.Game.Util;

public abstract class Game {

	private String name;
	private int totalPlayers;
	
	public Game(String name, int totalPlayers) {
		this.name = name;
		this.totalPlayers = totalPlayers;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxPlayers() {
		return totalPlayers;
	}
	
	public abstract void start();
	
	public abstract void end();
	
	public abstract boolean isSolo();
}
