package com.source.main.Utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
	
	public static ItemStack createItem(Material material) {
		ItemStack item = new ItemStack(material);
		return item;
	}
	
	public static ItemStack createItem(Material material, String itemName) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(itemName);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createItem(Material material, int amount) {
		ItemStack item = new ItemStack(material, amount);
		return item;
	}
	
	public static ItemStack createItem(Material material, String itemName, int amount) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(itemName);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ArrayList<String> createLore(String string){
		int sub = 0;
		ArrayList<String> strings = new ArrayList<>();
		for (int i = 0; i < string.length(); i++) {
			if (i % 39 == 0&&i!=0) {
				for (int b = i; b < string.length(); b++) {
					if (string.charAt(b) == ' ') {
						i = b+1;
						break;
					}
					if (b+1==string.length()) {
						i = b;
					}
				}
				strings.add(string.substring(sub, i));
				sub = i;
			}
		}
		
		return strings;
	}
}
