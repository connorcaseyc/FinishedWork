package com.dealership.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.dealership.data.ConnectionUtility;
import com.dealership.model.User;
import com.dealership.ui.CustomerMenu;
import com.dealership.ui.EmployeeMenu;
import com.dealership.ui.Operate;
import com.dealership.ui.TopMenus;

public class loginSQL {
	
	public static String loggedIn="";
	
	public static List<User> readSQLUsers() {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "select * from users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> uList = new ArrayList<>();
			while(rs.next()) {
				uList.add(new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			ps.close();
			conn.close();
			return uList;
		} catch(SQLException e) {
//			e.printStackTrace();
			Operate.log.info("User List Not Found");
		}
		return null;
	}
	
	public static void addUser(Scanner scan, int a) {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "insert into users (user_type, user_name, user_pass) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a);
			System.out.println("What is your preferred username?");
			ps.setString(2, scan.next());
			System.out.println("What is your preferred password?");
			ps.setString(3, scan.next());
			ps.executeUpdate();
			ps.close();
			conn.close();
			System.out.println("Account created!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void findUser(Scanner scan) {
		List<User> uList = readSQLUsers();
		String loginMsg = "Please enter your username: ";
		String incorrect = "Unrecognized login information\n"
				+ "(If you need to create an account select new user from the main menu)\n"
				+ "Returning to the previous menu...\n";
		String checkInfo = "";
		String loginInput = "";
		System.out.println(loginMsg);
		while(!checkInfo.equals("done")) {
			loginInput = scan.next();
			int length = uList.size();
			for(int i=0; i<length; i++) {
				Object testUser = uList.get(i);
				String testName = uList.get(i).getUserName();
				String testPass = uList.get(i).getUserPass();
				int testType = uList.get(i).getUserType();
				
				if(loginInput.equals(testName)) {
					System.out.println("Username ["+testName+"] recognized");
					System.out.println("Please enter the password: ");
					String loginPass = scan.next();
					
					if(loginPass.equals(testPass)) {
						loggedIn=testName;
						System.out.println("Login Confirmed!\n");
						//System.out.println("User is: "+loggedIn);
						
						if(testType==1) {
							CustomerMenu.custMenu(scan);
							TopMenus.topMenu(scan);
							checkInfo="done";
							System.exit(0);
							break;
						} else if(testType==2) {
							EmployeeMenu.empMenu(scan);
							TopMenus.topMenu(scan);
							checkInfo="done";
							System.exit(0);
							break;
						} else if(testType==3) {
							System.out.println("CREATE MANAGER MENU");
							TopMenus.topMenu(scan);
							checkInfo="done";
							System.exit(0);
							break;
						} else {
							System.out.println("User type is incorrect, fix user account");
							TopMenus.topMenu(scan);
							checkInfo="done";
							break;
						}
					}

				}
				
			}
			Operate.log.info("Account Not Found");
			checkInfo="done";
			System.out.println(incorrect);
		}	
	}

}
