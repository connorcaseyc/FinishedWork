package com.dealership.ui;

import java.util.Scanner;

import com.dealership.model.User;
import com.dealership.service.loginSQL;

public class TopMenus {

	public static void topMenu(Scanner scan) {
		String topMenu = "Welcome to the Tampa Dealership, "
				+ "are you a new or existing user?\n"
				+ "Enter 'N' for New User\n"
				+ "Enter 'E' for Existing User\n"
				+ "To end the program enter 'done' ";
		String input3 = "";
		System.out.println(topMenu);
		while(!input3.equals("DONE")) {
			input3 = scan.next().toUpperCase();
			switch(input3) {
			case "N":
				System.out.println("We are glad to have you!");
				buildUser(scan);
				System.out.println(topMenu);
				break;
			case "E":
				System.out.println("\nWelcome back!");
				loginSQL.findUser(scan);
				System.out.println(topMenu);
				break;
			case "DONE":
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Try again");
				break;
			}
		}
	}
	
	public static void buildUser(Scanner scan) {
		String buildMenu = "Are you a customer or employee?\n"
				+ "Enter 'C' for Customer\n"
				+ "Enter 'E' for employee\n"
				+ "To end the program enter 'done' ";
		String input1 = "";
		System.out.println(buildMenu);
		while(!input1.equals("DONE")) {
			input1 = scan.next().toUpperCase();
			switch(input1) {
			case "C":
				int a = 1;
				loginSQL.addUser(scan, a);
				System.out.println("Returning to the main menu...");
				input1="DONE";
				break;
			case "E":
				a = 2;
				loginSQL.addUser(scan, a);
				System.out.println("Returning to the main menu...");
				input1="DONE";
				break;
			case "DONE":
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Try again");
				break;
			}
		}	
	}
	
}
