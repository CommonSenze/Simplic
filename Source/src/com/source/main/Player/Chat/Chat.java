package com.source.main.Player.Chat;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.source.main.Main;
import com.source.main.Player.User;

import net.md_5.bungee.api.ChatColor;

public class Chat {

	private Main plugin;
	
	public Chat(Main plugin) {
		this.plugin = plugin;
	}
	
	public void chat(AsyncPlayerChatEvent e) {
		User user = plugin.getUserManager().get(e.getPlayer());
		
		if (user.isMuted()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "You are muted.");
			return;
		}
		
		if (!user.getPrefix().isEmpty()) {
			e.getPlayer().setDisplayName(user.getPrefix() + e.getPlayer().getName());
		} else if (user.getGroup()!= null) {
			e.getPlayer().setDisplayName(user.getGroup().getPrefix() + e.getPlayer().getName());
		}
	}
}
