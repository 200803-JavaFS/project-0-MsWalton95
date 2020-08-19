package project0;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.revature.model.*;

public class CustomerServiceTest{
	Account a = new Account();
	Customer c = new Customer();
	Transaction t = new Transaction();
	Scanner sc = new Scanner(System.in);

	

	@Before
	public void beforeTest() {System.out.println("Before Test");}
	
	@Test
	public void withdraw() {
			
			double money = 50;
		double balance = 100.00;

			
			if(money > balance) {
				System.out.println("\n Withdrawn cannot be more than balance");
			
			}else if(money > 1000) {
				System.out.println("\n Withdrawn cannot be more than 1000 per day");
				
			}else if(money <= 0) {
				System.out.println("\n Withdrawn cannot be a negative value");
			
			}else {

				System.out.println();

				}
	}

/*---------------------------------------------------------------------------------------------------------*/
	@Test
	public void deposit() {

			double money = 200.00;
			
			if(money <= 0) {
				System.out.println("\n Deposit cannot be a negative value");

			}else {

					System.out.println("Success");
				
			}
	}
		
/*---------------------------------------------------------------------------------------------------------*/
	@Test
	public void transfer() {
		
			double balance = 300.00;
			double money = 50.00;
			
			if(money > balance) {
				System.out.println("\n Transfer cannot be more than balance");
			
			}else if(money <= 0) {
				System.out.println("\n Transfer cannot be a negative value");
			
			}else {

			System.out.println("Success");
			}

	}
	/*---------------------------------------------------------------------------------------------------------*/			

	@Test
	public void getCustomer() {	
				c.setUserID(2);
				c.setUserName("user1");
				c.setPassWord("user1");
				c.setFirstName("John");
				c.setLastName("Doe");
				c.setEmail("johndoe@gmail.com");
				c.setNumber(1234567891);
	
				System.out.println(c);
		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	@Test
	public void getAccount() {	
				a.setAccID(1);
				a.setAccName("default");
				a.setBalance(350.00);
				a.setAccType("Savings");
				a.setApproved(true);
				a.setUserID(1);

				System.out.println(a);
	}
	/*---------------------------------------------------------------------------------------------------------*/		
	@Test
	public void getTransaction() {
		
			List<Transaction> transactions = new ArrayList<Transaction>();
			
		
				t.setUserID(1);
				t.setAccType("Checking");
				t.setAmount(250.00);
				t.setTotalBalance(1050);
				t.setTransID(2);
				
				transactions.add(t);
				System.out.println(t);
	}
/*---------------------------------------------------------------------------------------------------------*/				

	@Test
	public void getAccounts() {	
			
			List<Account> accounts = new ArrayList<Account>();
			
				a.setAccID(1);
				a.setAccName("vacation");
				a.setBalance(50.00);
				a.setAccType("Savings");
				a.setApproved(false);
				a.setUserID(1);
				
				accounts.add(a);
				System.out.println(a);
	}
	
	/*---------------------------------------------------------------------------------------------------------*/	


		 @Test
	public void transOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Transaction History \n 2. Deposit \n 3. Withdraw \n 4. Transfer \n 5. Exit");
		System.out.println("\n ----------------------------------- \n");

		int choice = 3;
		try {
			switch(choice) {
				case 1: 
					getTransaction();
					break;
				case 2:
					deposit();

					break;
				case 3:
					withdraw();

					break;
				case 4:
					transfer();

					break;
				case 5:
					
					break;
				default: 
					System.out.println(" Invalid input"); 
			}
		}catch(InputMismatchException e) {

		}catch(Exception e) {
			e.printStackTrace();

		}
	}

/*---------------------------------------------------------------------------------------------------------*/	
	@Test
	public void logout() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" Thank You For Using Revature Bank!");
		System.out.println("\n ----------------------------------- \n");
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

}
