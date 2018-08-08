package com.source.main.Commands.Util;

import org.bukkit.command.CommandSender;

public abstract class Commands {

	private String command, description;
	
	public Commands(String command, String description) {
		this.command = command;
		this.description = description;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getDescription() {
		return description;
	}
	
	public abstract void run(CommandSender sender, String[] args);
}
