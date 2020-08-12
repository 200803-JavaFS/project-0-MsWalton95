package project0;

import static org.junit.Assert.assertTrue;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.*;

import com.revature.services.UserService;

public class UserServiceTest {
	public static UserService us; 
	public static Scanner sc = new Scanner(System.in);
	
	
	@Test
	public void login() {
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					us.signin(); break;
				case 2:
					us.logout(); break;
				default: 
					System.out.println("Wrong input. Try again"); 
					login();
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void signin() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		try {
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			String password = sc.next();
			
			System.out.println();
	
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
