package com.source.main.Utils;

import org.bukkit.scheduler.BukkitRunnable;

import com.source.main.Main;
import com.source.main.Game.Game;

public class Countdown extends BukkitRunnable {

	private Main plugin;
	private Game game;
	private int time;
	
	public Countdown(Game game, Main plugin, int time) {
		this.plugin = plugin;
		this.time = time;
	}
	
	public void run() {
		time--;
		if (time %15 == 0 &&time!=0) {
			
		} else if (time <= 5 && time != 0) {
			
		} else if (time == 0) {
			game.start();
		}
	}
}
