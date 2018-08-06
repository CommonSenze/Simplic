package com.source.main.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.source.main.Main;
import com.source.main.Commands.Util.Commands;

public class CommandManager implements CommandExecutor {

	private ArrayList<Commands> commands = new ArrayList<>();
	
	public CommandManager(Main plugin) {}
	
	public void add(Commands command) {
		if (Bukkit.getPluginCommand(command.getCommand()) != null) {
			commands.add(command);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		for (Commands command : commands) {
			try {
				command.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
