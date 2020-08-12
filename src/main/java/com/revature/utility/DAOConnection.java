package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOConnection {
	
//	final String USERNAME = "postgres";
//	final String PASSWORD = "Fairytail18!";
//	final String URL = "jdbc:postgresql://localhost:3000/bank";
//	final String DRIVER ="org.postgresql.Driver";
//	
//	try {
//		Class.forName(DRIVER);
//		
//		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//		
//		Statement stmt = conn.createStatement();
//		
//		ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
//		while(rs.next()) {
//			System.out.print(rs.getInt(1));
//			System.out.print(rs.getString(2));
//			System.out.print(rs.getString(3));
//			System.out.println(rs.getString(4));
//		}
//		
//		conn.close();
//		
//	}catch(Exception e){
//		System.out.println(e);
//	}

}
