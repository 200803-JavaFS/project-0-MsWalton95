package project0;

import org.junit.Before;
import org.junit.Test;


public class AdminServiceTest{

		
/*---------------------------------------------------------------------------------------------------------*/	
	@Before
	public void beforeTest() {System.out.println("Before Test");}

/*---------------------------------------------------------------------------------------------------------*/	
	@Test
	public void updateAccountName() {
			String accName = "new shoes";
			
			if(accName.equals("default")) {
				System.out.println(" Cannot change to 'default' account");
			}else {
				System.out.println("Success");
			}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	@Test
	public void infoOption() {
		int choice = 3;
		switch(choice) {
			case 1: 
				System.out.print("Get all customers ");
				break;
			case 2: 
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print("Please enter customer ID: ");
				break;
			case 3:
				System.out.println("update customer");
				break;
			default: 
				System.out.println(" Invalid input"); 
				
		}
	} 
}

