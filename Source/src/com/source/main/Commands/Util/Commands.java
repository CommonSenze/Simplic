package com.source.main.Commands.Util;

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
	
	public abstract void run();
}
