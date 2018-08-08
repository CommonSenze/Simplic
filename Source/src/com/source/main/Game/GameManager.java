package com.source.main.Game;

import java.util.ArrayList;
import java.util.UUID;

import com.source.main.Main;
import com.source.main.Game.Util.Game;
import com.source.main.Utils.Countdown;
import com.source.main.Utils.Function;

public class GameManager extends Function {

	private Game startedGame;
	private ArrayList<Game> games = new ArrayList<>();
	private ArrayList<UUID> gamers = new ArrayList<>();
	private Countdown countdown;
	private boolean finished = true;
	
	public GameManager(Main plugin) {
		plugin.add(this);
	}
	
	public void add(Game game) {
		games.add(game);
	}
	
	public Game getStartedGame() {
		return startedGame;
	}
	
	public boolean hasStartedGame() {
		return startedGame != null;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public Countdown getCountdown() {
		return countdown;
	}
	
	public void setCountdown(Countdown countdown) {
		this.countdown = countdown;
	}
	
	public void addGamer(UUID uuid) {
		gamers.add(uuid);
	}
	
	public void removeGamer(UUID uuid) {
		gamers.remove(uuid);
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void setStartedGame(Game startedGame) {
		this.startedGame = startedGame;
	}

	@Override
	public void unload() {
		games.clear();
		gamers.clear();
	}
}
