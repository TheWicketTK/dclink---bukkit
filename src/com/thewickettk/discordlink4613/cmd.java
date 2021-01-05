package com.thewickettk.discordlink4613;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thewickettk.discordlink4613.mysql.MySQLCONNECT;
import com.thewickettk.discordlink4613.mysql.MySQLStatment;

public class cmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("discord")){
			if (sender instanceof Player){
				if (args.length == 0){
					sender.sendMessage(msg.getColor("&8&m&l+=========+&f &c&lCentrixPvP &b&lDISCORD &8&m&l+=========+", false));
					sender.sendMessage(msg.getColor("", false));
					sender.sendMessage(msg.getColor("&bYou can link your discord and minecraft account by", false));
					sender.sendMessage(msg.getColor("&busing &c/discord link&b.", false));
					sender.sendMessage(msg.getColor("", false));
					sender.sendMessage(msg.getColor("&8&l&m+===================================+", false));
					return false;
				}
				if (args.length == 1){
					if (args[0].equalsIgnoreCase("help")){
						sender.sendMessage(msg.getColor("&8&m&l+=========+&f &c&lCentrixPvP &b&lDISCORD &8&m&l+=========+", false));
						sender.sendMessage(msg.getColor("", false));
						sender.sendMessage(msg.getColor("&bYou can link your discord and minecraft account by", false));
						sender.sendMessage(msg.getColor("&busing &c/discord link&b.", false));
						sender.sendMessage(msg.getColor("", false));
						sender.sendMessage(msg.getColor("&8&l&m+===================================+", false));
						return false;
					}
					if (args[0].equalsIgnoreCase("link")){
						MySQLCONNECT.mysqlConnect();
						if (MySQLStatment.checkPlayer(sender.getName())){
							if (!MySQLStatment.checkUsed(sender.getName())){
								sender.sendMessage(msg.getColor(Main.config.getString("msg.havekey") + MySQLStatment.getKey(sender.getName()), true));
								MySQLCONNECT.mysqlClose();
								return false;
							}
							sender.sendMessage(msg.getColor(Main.config.getString("msg.keybeenused"), true));
							MySQLCONNECT.mysqlClose();
							return false;
						}
						String key = GenKey.genRandomKey();
						while (MySQLStatment.checkKey(key)){
							key = GenKey.genRandomKey();
						}
						MySQLStatment.linkAccount(sender.getName(), key);
						if (MySQLStatment.checkPlayer(sender.getName())){
							sender.sendMessage(msg.getColor(Main.config.getString("msg.keysuccessful") + key, true));
							MySQLCONNECT.mysqlClose();
							return false;
						}
						sender.sendMessage(msg.getColor(Main.config.getString("msg.error"), true));
						MySQLCONNECT.mysqlClose();
						return false;
					}
					sender.sendMessage(msg.getColor(Main.config.getString("msg.subcommandnotfound"), true));
					return false;
				}
				sender.sendMessage(msg.getColor(Main.config.getString("msg.subcommandnotfound"), true));
				return false;
			} else {
				System.out.println("Not a Console comamnd!");
			}
		}
		return false;
	}

}
