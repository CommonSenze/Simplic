package com.source.main;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.source.main.Player.Permissions.Permissions;

import me.commonsenze.main.Util.Config;
import me.commonsenze.main.Util.EventUtil;
import me.commonsenze.main.Util.Function;
import me.commonsenze.main.Util.SQLConnection;
import me.commonsenze.main.Util.Source;
import me.commonsenze.main.Util.Managers.CommandManager;
import me.commonsenze.main.Util.Managers.GameManager;
import me.commonsenze.main.Util.Managers.SuperBoardManager;

public class Main extends JavaPlugin {

	private ArrayList<Function> functions = new ArrayList<>();
	private static Main instance;
	private SQLConnection sqlConnection;
	private Permissions permissions;
	private EventUtil eventUtil;
	private GameManager gameManager;
	private CommandManager commandManager;
	private SuperBoardManager superBoardManager;
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		Config.register(this);

		sqlConnection = new SQLConnection();
		permissions = new Permissions(this);
		eventUtil = new EventUtil(this);
		gameManager = new GameManager(this);
		commandManager = new CommandManager(this);
		superBoardManager = new SuperBoardManager();
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
	
	public void remove(Function function) {
		functions.remove(function);
	}
	
	public Source getSource() {
		return Source.getInstance();
	}
	
	public SuperBoardManager getSuperBoardManager() {
		return superBoardManager;
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
