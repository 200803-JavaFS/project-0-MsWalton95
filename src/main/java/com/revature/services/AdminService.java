package com.revature.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.revature.model.Admin;
import com.revature.model.Employee;
import com.revature.utility.ConnectionDAO;

public class AdminService extends EmployeeService {
	Admin a = new Admin();

	
	public void approveAccount() {}
	
	public void denyAccount() {}
	
	public void cancelAccount() {}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println("ADMINISTRATOR LOGIN \n");
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			String password = sc.next();
			
			System.out.println();
			userLogin(username,password);
			
	
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public Employee userLogin(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM employee WHERE user_name=? AND pass_word=? AND administator=true;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
	
			if(rs.next()) {
				e.setEmployeeID(rs.getInt("employee_id"));
	
				System.out.println("Welcome Administator #" + e.getEmployeeID()+ "!");
				homePage();
				return e;
			}else {
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

}


