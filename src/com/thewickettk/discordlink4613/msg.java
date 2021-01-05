package com.thewickettk.discordlink4613;

import org.bukkit.ChatColor;

public class msg {
	private static String out;
	
	public static String getColor(String msg,boolean prefix){
		if (!prefix){
			out = (ChatColor.translateAlternateColorCodes('&', msg));
			return out;
		}
		out = (ChatColor.translateAlternateColorCodes('&',Main.config.getString("msg.prefix") + msg));
		return out;
	}
}
