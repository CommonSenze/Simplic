package com.source.main.Player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.source.main.Main;
import com.source.main.Utils.Function;

public class UserManager extends Function {

	private Main plugin;
	private HashMap<UUID, User> users = new HashMap<>();
	
	public UserManager(Main plugin) {
		this.plugin = plugin;
		plugin.add(this);
	}
	
	public void add(Player player) {
		users.put(player.getUniqueId(), new User(player, plugin));
	}
	
	public void remove(Player player) {
		users.remove(player.getUniqueId());
	}
	
	public boolean hasUser(UUID uuid) {
		return users.containsKey(uuid);
	}
	
	public void unload() {
		users.clear();
	}
}
