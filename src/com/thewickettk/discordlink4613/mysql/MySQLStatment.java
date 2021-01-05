package com.thewickettk.discordlink4613.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.thewickettk.discordlink4613.Main;

public class MySQLStatment {
	private static String sql;
	private static String out;
	
	public static boolean checkPlayer(String name){
		sql = "SELECT * FROM " + Main.config.getString("mysql.table") + " WHERE username='" + name + "' LIMIT 1";
		try{
			java.sql.PreparedStatement statement = MySQLCONNECT.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				statement.close();
				rs.close();
				return true;				
			}
			statement.close();
			rs.close();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public static String getKey(String name){
		sql = "SELECT * FROM " + Main.config.getString("mysql.table") + " WHERE username='" + name + "' LIMIT 1";
		try{
			java.sql.PreparedStatement statement = MySQLCONNECT.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				out = rs.getString("ukey");
				statement.close();
				rs.close();
				return out;				
			}
			statement.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static boolean checkUsed(String name){
		sql = "SELECT * FROM " + Main.config.getString("mysql.table") + " WHERE username='" + name + "' LIMIT 1";
		try{
			java.sql.PreparedStatement statement = MySQLCONNECT.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				if(rs.getBoolean("isused")){
					statement.close();
					rs.close();
					return true;
				}
			}
			statement.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	public static boolean checkKey(String key){
        try {
    		sql = "SELECT * FROM " + Main.config.getString("mysql.table")+ " WHERE ukey='"+ key +"' LIMIT 1";
			java.sql.PreparedStatement statement = MySQLCONNECT.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
					statement.close();
					rs.close();
					return true;
			}
			statement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
	public static void linkAccount(String name,String key){
		sql = "INSERT INTO " + Main.config.getString("mysql.table") + " (ukey,username,isused) VALUES ('" + key + "','" + name +"','" + 0 + "')";
        try {
			java.sql.PreparedStatement statement = MySQLCONNECT.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
