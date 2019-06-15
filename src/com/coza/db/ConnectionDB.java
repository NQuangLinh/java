package com.coza.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	private static String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=DBCoZaSTORE;"
			+ "integratedSecurity=true";
	private static String USER_NAME = "sa";
	private static String PASSWORD = "123";

	public static Connection getConnectionDB() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

}
