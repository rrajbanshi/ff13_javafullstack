package com.examples.empapp.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	private static Connection conn = null ;
	static final String LOCAL_DB_URL = "jdbc:mysql://localhost:3307/mydata";
	static final String USER = "root";
	static final String PASS = "root1234";
	private JdbcConnection() {};
	public static Connection getJdbcConnection() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(LOCAL_DB_URL, USER, PASS);
				System.out.println("Connection Created");
				return conn ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn ; 
}
}
