package com.thewickettk.discordlink4613;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.thewickettk.discordlink4613.mysql.MySQLCONNECT;

public class Main extends JavaPlugin{
	public static FileConfiguration config;
	
	public void onEnable(){
		File file = new File(getDataFolder() + "config.yml");
		if (!(file.exists())){
			System.out.println("Config not found!");
			this.saveResource("config.yml", false);
			System.out.println("Config Generated!");
		}
		config = getConfig();
		System.out.println("Config Loaded!");
		MySQLCONNECT.mysqlConnect();
		getCommand("discord").setExecutor(new cmd());
	}
}
