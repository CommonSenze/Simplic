package com.source.main.Utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.source.main.Main;

public class Config {

	private static Main plugin;
	private File file;
	private FileConfiguration config;
	
	public Config(String name) {
		this("",name);
	}
	
	public Config(String folder, String name) {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		String[] folders = folder.split(File.separator);
		
		String s = "";
		
		for (int i = 0; i < folders.length; i++) {
			File fold = new File(plugin.getDataFolder()+s, folders[i]);
			if (!fold.exists())fold.mkdir();
			s += File.separator + folders[i];
		}
		
		file = new File(plugin.getDataFolder() +folder, file+".yml");

		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (Exception e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create "+file+".yml!");

			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}

	public void saveConfig() {
		try {
			config.save(file);
		} catch (Exception e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save "+file.getName());
		}
	}

	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public static void register(Main plugin) {
		Config.plugin = plugin;
	}
}
