package com.estore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	private static final String URL = "jdbc:mysql://localhost:3306/estore?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root"; // REPLACE with your username
	private static final String PASSWORD = "EECS4413"; // REPLACE with your password

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
