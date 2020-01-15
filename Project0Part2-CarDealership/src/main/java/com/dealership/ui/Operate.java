package com.dealership.ui;

import java.util.Scanner;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Operate extends TopMenus {
	
	static Scanner scan = new Scanner(System.in);
	public static Logger log = Logger.getLogger(Operate.class.getName());
	
	public static void main(String[] args) {
		topMenu(scan);
	}

}
