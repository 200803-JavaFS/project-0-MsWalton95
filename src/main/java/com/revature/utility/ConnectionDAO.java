package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDAO {
	
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "password";
	private static final String URL = "jdbc:postgresql://javafs200803.czjvsq707ohh.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String DRIVER ="org.postgresql.Driver";
	
	public static Connection connect() throws SQLException {
			try {
				Class.forName(DRIVER);
				
			} catch (ClassNotFoundException e) {
				System.out.println("Unable to find driver");
			}
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}

//Statement stmt = conn.createStatement();
//
//ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
//
//while(rs.next()) {
//	System.out.print(rs.getString("first_name"));
//	System.out.println(rs.getString("last_name"));
//}
//conn.close();