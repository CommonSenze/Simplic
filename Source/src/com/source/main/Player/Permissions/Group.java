package com.source.main.Player.Permissions;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;

import com.source.main.Main;
import com.source.main.Utils.Function;

public class Group extends Function {

	private Main plugin;
	private String name, prefix, tabPrefix;
	private ArrayList<String> permissions = new ArrayList<>();
	private ConfigurationSection section;
	private int ranking;
	
	public Group(String name, Main plugin) {
		this.plugin = plugin;
		this.name = name;
		
		plugin.add(this);
		
		section = plugin.getPermissions().getSection(this);
		
		if (!section.contains("Prefix"))
			section.set("Prefix", "");
		
		if (!section.contains("Tab Prefix"))
			section.set("Tab Prefix", "");
		
		prefix = section.getString("Prefix");
		tabPrefix = section.getString("Tab Prefix");
		ranking = section.getInt("Ranking");
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getTabPrefix() {
		return tabPrefix;
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setTabPrefix(String tabPrefix) {
		this.tabPrefix = tabPrefix;
	}
	
	public ArrayList<String> getPermissions(){
		return permissions;
	}
	
	public void add(String permission) {
		permissions.add(permission);
		plugin.getPermissions().reload();
	}
	
	public void unload() {
		
	}
}
