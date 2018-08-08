package com.source.main.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.source.main.Main;
import com.source.main.Commands.Util.Commands;
import com.source.main.Utils.Function;
import com.source.main.Utils.Settings;

public class CommandManager extends Function implements CommandExecutor {

	private Main plugin;
	private ArrayList<Commands> commands = new ArrayList<>();

	public CommandManager(Main plugin) {
		this.plugin = plugin;
		plugin.add(this);
	}

	public void add(Commands command) {
		if (Bukkit.getPluginCommand(command.getCommand()) != null) {
			commands.add(command);
			plugin.getLogger().info(Settings.PLUGIN_PREFIX + "/"+ command.getCommand() + " has been added to list of commands.");
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		for (Commands command : commands) {
			if (command.getCommand().equalsIgnoreCase(cmd.getName())) {
				try {
					command.run(sender, args);
				} catch (Exception e) {
					sender.sendMessage(Settings.UNKNOWN_ERROR);
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	

	@Override
	public void unload() {
		commands.clear();
	}
}
