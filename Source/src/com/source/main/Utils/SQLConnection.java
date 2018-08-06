package com.source.main.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

	private String host;
	private int port;
	private String username;
	private String password;
	private String database;
	private Connection connection;
	
	public SQLConnection() {
		host = "198.154.108.70"; //EX: 127.0.0.1
        port = 3306; //YOUR DATABASE PORT, TYPICALLY 3306.
        username = "mc44819"; //DATABASE USERNAME
        password = "a4292adb72"; //DATABASE PASSWORD
        database = "mc44819"; //DATABASE NAME
	}
	
	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database, this.username, this.password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return connection;
	}
}
