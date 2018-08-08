package com.source.main.Player;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import com.source.main.Main;
import com.source.main.Player.Permissions.Group;
import com.source.main.Utils.Config;
import com.source.main.Utils.Function;
import com.source.main.Utils.Settings;

import net.md_5.bungee.api.ChatColor;

public class User extends Function {

	private Main plugin;
	private Player player;
	private int money;
	private Group group;
	private String customPrefix;
	private Config config;
	private boolean muted;
	
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
		config = new Config(File.separator + "Users"+File.separator + player.getUniqueId(), "Information");
		try {
			Connection connection = plugin.getSQLConnection().connect();
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE UUID = ?");
			
			statement.setString(1, player.getUniqueId().toString().replaceAll("-", ""));
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				money = result.getInt("Money");
				group = plugin.getPermissions().getGroup(result.getString("Group"));
			} else {
				money = 0;
			}
			
			connection.close();
		} catch (Exception e) {
			plugin.getLogger().severe(Settings.UNKNOWN_ERROR);
			
			e.printStackTrace();
		}
		
		if (!config.getConfig().contains("Prefix"))
			config.getConfig().set("Prefix", "");
		
		customPrefix = config.getConfig().getString("Prefix");
	}
	
	public String getJoinMessage() {
		return ChatColor.GREEN + "» " +ChatColor.GRAY + getPlayer().getName() + " has connected";
	}
	
	public String getQuitMessage() {
		return ChatColor.RED + "» " +ChatColor.GRAY + getPlayer().getName() + " has disconnected";
	}
	
	public int getMoney() {
		return money;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public boolean hasGroup() {
		return group!= null;
	}
	
	public String getPrefix() {
		return customPrefix;
	}
	
	public boolean isMuted() {
		return muted;
	}
	
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	
	public void setPrefix(String customPrefix) {
		this.customPrefix = customPrefix;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public void unload() {
		try {
			Connection connection = plugin.getSQLConnection().connect();
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE UUID = ?");
			
			statement.setString(1, player.getUniqueId().toString().replaceAll("-", ""));
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				PreparedStatement stmt = connection.prepareStatement("UPDATE users SET Money = ?, Group = ?, Player = ? WHERE UUID = ?");
				
				stmt.setInt(1, money);
				stmt.setString(2, (hasGroup() ? group.getName() : "NULL"));
				stmt.setString(3, player.getName());
				stmt.setString(4, player.getUniqueId().toString().replaceAll("-", ""));
				
				stmt.executeUpdate();
			} else {
				PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (Player, UUID, Money, Group) VALUES (?,?,?,?)");
				
				stmt.setString(1, player.getName());
				stmt.setString(2, player.getUniqueId().toString().replaceAll("-", ""));
				stmt.setInt(3, money);
				stmt.setString(4, (hasGroup() ? group.getName() : "NULL"));
				
				stmt.execute();
			}
			
			connection.close();
		} catch (Exception e) {
			plugin.getLogger().severe(Settings.UNKNOWN_ERROR);
			
			e.printStackTrace();
		}
	}
}
