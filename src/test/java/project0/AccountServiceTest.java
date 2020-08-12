package project0;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Test;

public class AccountServiceTest {
	double balance = 100.00;
	Scanner sc = new Scanner(System.in);
	
	@Test
	public void withdraw() {
		try {
			int money = sc.nextInt();
			
			if(money <= 0) {
				System.out.println("Cannot withdraw negative value");
				
			}else if(money > balance) {
				System.out.println("Cannot withdraw more than balance");
			}else {
				balance-= money;
				System.out.println("Withdraw: " + money);
				System.out.println("Current Balance: " + balance);
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deposit() {
		try {
			int money = sc.nextInt();
			
			if(money <= 0) {
				System.out.println("Cannot deposit negative value");
			}else {
				balance+= money;
				System.out.println("Deposit: " + money + "\n Current Balance: " + balance);
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
