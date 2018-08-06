package com.source.main.Utils;

import com.source.main.Main;
import com.source.main.Game.Game;

public class EventUtil {

	private Main plugin;
	
	public EventUtil(Main plugin) {
		this.plugin = plugin;
	}
	
	public void startCountdown(Game game, int time) {
		Countdown countdown = new Countdown(game, plugin, time);
		
	}
	
}
