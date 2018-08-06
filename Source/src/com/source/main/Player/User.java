package com.source.main.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import com.source.main.Main;
import com.source.main.Utils.Function;

import net.md_5.bungee.api.ChatColor;

public class User extends Function {

	private Main plugin;
	private Player player;
	
	public User(Player player, Main plugin) {
		this.player = player;
		this.plugin = plugin;
		plugin.add(this);
		load();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void load() {
		try {
			Connection connection = plugin.getSQLConnection().connect();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE UUID = ?");
			statement.setString(1, player.getUniqueId().toString().replaceAll("-", ""));
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				
			} else {
				
			}
			
		} catch (Exception e) {
			//TODO getPlayer().sendMessage();
			e.printStackTrace();
		}
	}
	
	public String getJoinMessage() {
		return ChatColor.GREEN + "» " +ChatColor.GRAY + getPlayer().getName() + " has connected";
	}
	
	public String getQuitMessage() {
		return ChatColor.RED + "» " +ChatColor.GRAY + getPlayer().getName() + " has disconnected";
	}
	
	public void unload() {
		
	}
}
