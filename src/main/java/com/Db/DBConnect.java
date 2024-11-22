package com.Db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital_management","root", "232321");
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
}
