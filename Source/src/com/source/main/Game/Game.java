package com.source.main.Game;

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
}
