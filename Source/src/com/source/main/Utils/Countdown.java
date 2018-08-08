package com.source.main.Utils;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.source.main.Main;
import com.source.main.Game.Util.Game;

import net.md_5.bungee.api.ChatColor;

public class Countdown extends BukkitRunnable {

	private Main plugin;
	private Game game;
	private int time;
	
	public Countdown(Game game, Main plugin, int time) {
		this.plugin = plugin;
		this.game = game;
		this.time = time;
	}
	
	public void run() {
		time--;
		if (time %15 == 0 &&time!=0) {
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + ChatColor.STRIKETHROUGH+ "------------------------");
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + "Game: "+ChatColor.GOLD+ game.getName());
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + "Starting in: "+ChatColor.GOLD+ getTimeStamp(time));
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + ChatColor.STRIKETHROUGH+ "------------------------");
		} else if (time <= 5 && time != 0) {
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + ChatColor.STRIKETHROUGH+ "------------------------");
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + "Game: "+ChatColor.GOLD+ game.getName());
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + "Starting in: "+ChatColor.GOLD+ getTimeStamp(time));
			Bukkit.broadcastMessage(ChatColor.WHITE.toString() + ChatColor.STRIKETHROUGH+ "------------------------");
		} else if (time == 0) {
			game.start();
			plugin.getGameManager().setStartedGame(game);
		}
	}
	
	public String getTimeStamp(int seconds) {
		int minutes = seconds / 60;
		seconds %= 60;
		
		return (minutes >= 1 ? minutes + "m " : "") + seconds+"s";
	}
}
