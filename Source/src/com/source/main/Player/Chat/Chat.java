package com.source.main.Player.Chat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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
		} else if (user.hasGroup()) {
			e.getPlayer().setDisplayName(user.getGroup().getPrefix() + e.getPlayer().getName());
		}
		
		if (e.getPlayer().hasPermission("simple.color"))
			
		
		e.setFormat("%s "+ChatColor.WHITE+"» %s");
		if (referringToPlayer(e.getMessage()))sendMessages(e);
	}
	
	public boolean referringToPlayer(String message){
		for (Player p : Bukkit.getOnlinePlayers()){
			if (message.contains(p.getName()))return true;
		}
		return false;
	}

	public ChatColor findColor(String message, String substring){
		ChatColor chat = ChatColor.WHITE;
		for (int i = 0; i < message.indexOf(substring)-1; i++){
			if (message.charAt(i) == '§'){
				if (Character.isDigit(message.charAt(i+1))){
					chat = ChatColor.getByChar(message.charAt(i+1));
				} else if (Character.isAlphabetic(message.charAt(i+1))){
					if (getAlphabeticColors().contains(message.charAt(i+1)+""))chat = ChatColor.getByChar(message.charAt(i+1));
				}
			}
		}
		return chat;
	}

	public ArrayList<String> getAlphabeticColors(){
		ArrayList<String> list = new ArrayList<>();
		for (ChatColor cc : ChatColor.values()){
			if (Character.isAlphabetic(cc.toString().charAt(1)))list.add(cc.toString().charAt(1)+"");
		}
		return list;
	}

	public void sendMessages(AsyncPlayerChatEvent e){
		ArrayList<Player> remove = new ArrayList<>();
		for (Player p : e.getRecipients()){
			if (e.getMessage().contains(p.getName()))remove.add(p);
		}
		e.getRecipients().removeAll(remove);
		for (Player p : remove){
			String message = e.getMessage().replace(p.getName(), ChatColor.GOLD + "@" + p.getName() + findColor(e.getMessage(), p.getName()));
			p.sendMessage((e.getFormat().replaceFirst("%s", e.getPlayer().getDisplayName()).replaceFirst("%s", message)));
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 10F, 1F);
		}
	}
}
