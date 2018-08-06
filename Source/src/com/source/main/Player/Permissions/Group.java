package com.source.main.Player.Permissions;

import org.bukkit.configuration.ConfigurationSection;

import com.source.main.Main;

public class Group {

	private Main plugin;
	private String name, prefix, tabPrefix;
	private ConfigurationSection section;
	
	public Group(String name, Main plugin) {
		this.plugin = plugin;
		this.name = name;
		
		section = plugin.getPermissions().getSection(this);
		
		prefix = section.getString("Prefix");
		tabPrefix = section.getString("Tab Prefix");
	}
}
