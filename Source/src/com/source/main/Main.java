package com.source.main;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.source.main.Player.UserManager;
import com.source.main.Player.Permissions.Permissions;
import com.source.main.Utils.Config;
import com.source.main.Utils.EventUtil;
import com.source.main.Utils.Function;
import com.source.main.Utils.SQLConnection;

public class Main extends JavaPlugin {

	private ArrayList<Function> functions = new ArrayList<>();
	private UserManager userManager;
	private SQLConnection sqlConnection;
	private Permissions permissions;
	private EventUtil eventUtil;
	
	public void onEnable() {
		Config.register(this);
		
		userManager = new UserManager(this);
		sqlConnection = new SQLConnection();
		permissions = new Permissions(this);
		eventUtil = new EventUtil(this);
	}
	
	public void onDisable() {
		for (Function function : functions) {
			function.unload();
		}
		functions.clear();
	}
	
	public void add(Function function) {
		functions.add(function);
	}
	
	public UserManager getUserManager() {	
		return userManager;
	}
	
	public SQLConnection getSQLConnection() {
		return sqlConnection;
	}
	
	public Permissions getPermissions() {
		return permissions;
	}
	
	public EventUtil getEventUtil() {
		return eventUtil;
	}
}
