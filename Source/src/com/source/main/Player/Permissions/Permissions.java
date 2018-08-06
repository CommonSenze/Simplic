package com.source.main.Player.Permissions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.source.main.Main;
import com.source.main.Utils.Config;
import com.source.main.Utils.Function;
import com.source.main.Utils.Settings;


public class Permissions extends Function {

	private Main plugin;
	private Config config;
	private HashMap<UUID, PermissionAttachment> permissions = new HashMap<>();	
	private ArrayList<Group> groups = new ArrayList<>();
	
	public Permissions(Main plugin) {
		this.plugin = plugin;
		config = new Config(Settings.PERMISSIONS_FILE);
		plugin.add(this);
	}
	
	public void injectPlayer(Player... pl) {
		for (Player p : pl) {
			if (permissions.get(p.getUniqueId()) == null) permissions.put(p.getUniqueId(), p.addAttachment(plugin));
			Group group = getPlayerGroup(p.getUniqueId());
			if (group != null) {
				for (Group g : getGroups()) {
					if (g.getRanking() < group.getRanking()) {
						for (String perm : g.getPermissions()) {
							permissions.get(p.getUniqueId()).setPermission(perm, true);
						}
					}
				}
				for (String perm : group.getPermissions()) {
					permissions.get(p.getUniqueId()).setPermission(perm, true);
				}
			}
		}
	}

	public void injectPlayer(Collection<? extends Player> pl) {
		for (Player p : pl) {
			getFile().getConfig().set("Users." + p.getUniqueId() + ".Name", p.getName());
			if (permissions.get(p.getUniqueId()) == null) permissions.put(p.getUniqueId(), p.addAttachment(plugin));

			Group group = getPlayerGroup(p.getUniqueId());
			if (group != null) {
				for (Group g : getGroups()) {
					if (g.getRanking() <= group.getRanking()) {
						for (String perm : g.getPermissions()) {
							permissions.get(p.getUniqueId()).setPermission(perm, true);
						}
					}
				}
				for (String perm : group.getPermissions()) {
					permissions.get(p.getUniqueId()).setPermission(perm, true);
				}
			}
		}
	}

	public void uninjectPlayer(Player pl) {
		if (permissions.get(pl.getUniqueId()) == null) return;
		pl.removeAttachment(permissions.get(pl.getUniqueId()));
		permissions.remove(pl.getUniqueId());
	}
	
	public void uninjectPlayer(Collection<? extends Player> pl) {
		for (Player p : pl) {
			if (permissions.get(p.getUniqueId()) == null) return;
			p.removeAttachment(permissions.get(p.getUniqueId()));
			permissions.remove(p.getUniqueId());
		}
	}
	
	public Group getPlayerGroup(UUID uuid) {
		return getGroup(getFile().getConfig().getString("Users."+uuid+".Group"));
	}
	
	public Group getGroup(String name) {
		for (Group group : groups) {
			if (group.getName().equalsIgnoreCase(name))return group;
		}
		return null;
	}
	
	public ArrayList<Group> getGroups(){
		return groups;
	}
	
	public void setupGroups() {
		if (getFile().getConfig().getConfigurationSection("Groups")!=null) {
			for (String s : getFile().getConfig().getConfigurationSection("Groups").getKeys(false)) {
				groups.add(new Group(s, plugin));
			}
			reload();
		}
	}
	
	public ConfigurationSection getSection(Group group) {
		return getFile().getConfig().getConfigurationSection("Groups."+group.getName());
	}
	
	public Config getFile() {
		return config;
	}
	
	public void reload() {
		config.reloadConfig();
		uninjectPlayer(Bukkit.getOnlinePlayers());
		injectPlayer(Bukkit.getOnlinePlayers());
	}
	
	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}
}
