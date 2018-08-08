package com.source.main.Game.Util;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.source.main.Utils.Cuboid;

public abstract class Solo extends Game {

	public Solo(String name, int totalPlayers) {
		super(name, totalPlayers);
	}
	
	@Override
	public boolean isSolo() {
		return true;
	}

	public abstract Location getSpawn();

	public abstract Cuboid getArena();
	
	public abstract ItemStack[] getContents();
	
	public abstract ItemStack[] getArmorContents();
	
	public abstract ArrayList<PotionEffect> getPotionEffects();
}
