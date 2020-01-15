package com.dealership.ui;

import java.util.Scanner;

import com.dealership.service.carLotSQL;
import com.dealership.service.offerSQL;
import com.dealership.service.paymentSQL;

public class EmployeeMenu {
	
	public static void empMenu (Scanner scan) {
		
		String empOptions = "Please choose an option below: \n"
				+"1. Add a car to the lot\n"
				+"2. Evaluate an offer\n"
				+"3. Remove a car from the lot\n"
				+"4. View the cars on the lot\n"
				+"5. View cars owned by customers\n"
				+"6. View all open payment plans\n"
				+"7. Return to the previous menu";
		int input4=0;
		System.out.println(empOptions);
		while(input4 != 7) {
			input4 = scan.nextInt();
			switch(input4) {
			case 1:
				carLotSQL.addCarToList(scan);
				System.out.println("\nReturning to the previous menu...");
				System.out.println(empOptions);
				break;
			case 2:
				carLotSQL.viewCarLot();
				offerSQL.viewOfferList();
				offerSQL.evaluateOffer(scan);
				System.out.println("\nReturning to the previous menu...");
				System.out.println(empOptions);
				break;
			case 3:
				carLotSQL.removeCarOnList(scan);
				System.out.println("\nReturning to the previous menu...");
				System.out.println(empOptions);
				break;
			case 4:
				carLotSQL.viewCarLot();
				System.out.println("\nReturning to the previous menu...");
				System.out.println(empOptions);
				break;
			case 5:
				carLotSQL.viewCustomerOwnedCars();
				System.out.println("\nReturning to the previous menu...");
				System.out.println(empOptions);
				break;
			case 6:
				carLotSQL.viewCustomerOwnedCars();
				paymentSQL.viewAllPayments();
				System.out.println("\nReturning to the previous menu...");
				System.out.println(empOptions);
				break;
			case 7:
				System.out.println("\nReturning to the previous menu...");
				break;
			default:
				System.out.println("Try again");
				break;
			}
		}
	}
	
}
