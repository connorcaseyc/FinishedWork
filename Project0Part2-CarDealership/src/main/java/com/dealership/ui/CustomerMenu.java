package com.dealership.ui;

import java.util.Scanner;

import com.dealership.service.carLotSQL;
import com.dealership.service.offerSQL;
import com.dealership.service.paymentSQL;

public class CustomerMenu {
	
	public static void custMenu(Scanner scan) {	
		
		String custOptions = "Please choose an option below: \n"
				+"1. View cars on the lot\n"
				+"2. Make an offer on a car\n"
				+"3. View owned cars\n"
				+"4. View remaining payments\n"
				+"5. Return to the previous menu";	
		int input5=0;
		System.out.println(custOptions);
		while(input5 != 5) {
			input5 = scan.nextInt();
			switch(input5) {
			case 1:
				carLotSQL.viewCarLot();
				System.out.println("\nReturning to the previous menu...\n");
				System.out.println(custOptions);
				break;
			case 2:
				offerSQL.addOffer(scan);
				System.out.println("\nReturning to the previous menu...\n");
				System.out.println(custOptions);
				break;
			case 3:
				paymentSQL.viewCustomerCars();
				System.out.println("\nReturning to the previous menu...\n");
				System.out.println(custOptions);
				break;
			case 4:
				paymentSQL.viewMonthlyPayments();
				System.out.println("\nReturning to the previous menu...\n");
				System.out.println(custOptions);
				break;
			case 5:
				input5=5;
				System.out.println("\nReturning to the previous menu...\n");
				break;
			default:
				System.out.println("Try again");
				break;
			}
		}
	}

}
