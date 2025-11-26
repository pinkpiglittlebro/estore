package com.estore.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static String URL;

	static {
		try {
			Properties props = new Properties();
			InputStream in = DB.class.getClassLoader().getResourceAsStream("database.properties");
			props.load(in);

			URL = props.getProperty("db.url");

			Class.forName("org.sqlite.JDBC");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL);
	}
}
