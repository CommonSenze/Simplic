package com.source.main.Utils;

import org.bukkit.scheduler.BukkitRunnable;

import com.source.main.Main;

public class EventUtil {

	private Main main;
	private int time = 120;
	
	public void Cooldown(String eventName) {
		new BukkitRunnable() {
			@Override
			public void run() {
				time--;
				
				if(time == 0) {
					
				}
			}
		}.runTaskTimer(main, 0, 20L);
		
	}
	
}
