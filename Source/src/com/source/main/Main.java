package com.source.main;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.source.main.Commands.CommandManager;
import com.source.main.Game.GameManager;
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
	private GameManager gameManager;
	private CommandManager commandManager;
	
	public void onEnable() {
		Config.register(this);
		
		userManager = new UserManager(this);
		sqlConnection = new SQLConnection();
		permissions = new Permissions(this);
		eventUtil = new EventUtil(this);
		gameManager = new GameManager(this);
		commandManager = new CommandManager(this);
	}
	
	public void onDisable() {
		for (Function function : functions) {
			getLogger().info("Unloading class - "+function.getClass().getName());
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
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public GameManager getGameManager() {
		return gameManager;
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
