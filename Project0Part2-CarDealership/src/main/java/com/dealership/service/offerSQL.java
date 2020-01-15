package com.dealership.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dealership.data.ConnectionUtility;
import com.dealership.model.Offer;

public class offerSQL {
	
	public static List<Offer> readSQLOffers() {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "select * from offers order by offer_car";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Offer> offerList = new ArrayList<>();
			while(rs.next()) {
				offerList.add(new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
			ps.close();
			conn.close();
			return offerList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void viewOfferList() {
		List<Offer> offerList = readSQLOffers();
		for(int i=0; i<offerList.size(); i++) {
			System.out.println(offerList.get(i));
		}
	}
	
	public static void addOffer(Scanner scan) {
		try {
			carLotSQL.viewCarLot();
			Connection conn = ConnectionUtility.connect();
			String sql = "insert into offers (offer_user, offer_car, offer_amt) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loginSQL.loggedIn);
			System.out.println("Enter the car id number you want to make an offer on: ");
			ps.setInt(2, scan.nextInt());
			System.out.println("Enter your offer amount: ");
			ps.setInt(3, scan.nextInt());
			ps.executeUpdate();
			ps.close();
			conn.close();
			System.out.println("Offer submitted!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void evaluateOffer(Scanner scan) {
		String evalMenu = "\nWould you like to Accept or Reject an offer?\n"
				+ "Enter 'A' to accept an offer\n"
				+ "Enter 'R' to reject an offer\n"
				+ "Enter 'done' to return to the previous menu";
		String inputE = "";
		System.out.println(evalMenu);
		while(!inputE.equals("DONE")) {
			inputE=scan.next().toUpperCase();
			switch(inputE) {
			case "A":
				System.out.println("Enter the offer ID number you want to approve: ");
				int offAcc = scan.nextInt();
				List<Offer> offerList = readSQLOffers();
				Offer chosen = pullOffer(offAcc);
				int offCarPrice = chosen.getOfferAmount();
				int offCarId = chosen.getCarId();
				String newCarOwner = chosen.getOfferUser();
				
				try {
					Connection conn = ConnectionUtility.connect();
					String sql = "update cars set owner = ?, price = ? where car_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, newCarOwner);
					ps.setInt(2, offCarPrice);
					ps.setInt(3, offCarId);
					ps.executeUpdate();
					ps.close();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				paymentSQL.addPayment(newCarOwner, offCarId, offCarPrice);
				
				try {
					Connection conn = ConnectionUtility.connect();
					String sql = "delete from offers where offer_car = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, offCarId);
					ps.executeUpdate();
					ps.close();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("\nSystem updated for new car owner and new payment plan!\n");
				System.out.println(evalMenu);
				break;
				
			case "R":
				System.out.println("Enter the offer ID number you want to reject: ");
				int offRej = scan.nextInt();
				try {
					Connection conn = ConnectionUtility.connect();
					String sql = "delete from offers where offer_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, offRej);
					ps.execute();
					ps.close();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				System.out.println(evalMenu);
				break;
				
			case "DONE":
				inputE="DONE";
				break;
				
			default:
				System.out.println("Try again");
				break;
			}
		}
		
	}
	
	public static Offer pullOffer(int offAcc) {
		List<Offer> offerList = readSQLOffers();
		for(int i=0; i<offerList.size(); i++) {
			if(offerList.get(i).getOfferId()==offAcc) {
				Offer chosen = offerList.get(i);
				return chosen;
			}
		}
		return null;
	}

}
