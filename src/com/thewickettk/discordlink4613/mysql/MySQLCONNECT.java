package com.thewickettk.discordlink4613.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.thewickettk.discordlink4613.Main;

public class MySQLCONNECT {
	public static java.sql.Connection connection;
	private static String ip;
	private static String port;
	private static String username;
	private static String password;
	private static String database;
	private static Boolean isConnected;
	
	public static void mysqlConnect(){
		ip = Main.config.getString("mysql.host");	
		port = Main.config.getString("mysql.port");
		database = Main.config.getString("mysql.database");
		username = Main.config.getString("mysql.username");
		password = Main.config.getString("mysql.password");
    	try {
	    if (connection != null && !connection.isClosed()) {
	        return;
	    }
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database, username, password);
	        isConnected = true;
	        return;
    	}catch(Exception e){
    	  System.err.println("(DiscordLink) Could not connect to database!");
    	  e.printStackTrace();
    	}
    	return;
	}
	public static void mysqlClose(){
		if (isConnected){
			try {
				connection.close();
				isConnected = false;
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}
	}
}
