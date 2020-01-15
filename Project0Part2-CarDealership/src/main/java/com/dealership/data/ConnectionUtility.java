package com.dealership.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	public static Connection connect() {
		Connection conn=null; 
		try {
			conn = DriverManager.getConnection(  
					System.getenv("URL"),
					System.getenv("USERNAME"),
					System.getenv("PASSWORD"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
