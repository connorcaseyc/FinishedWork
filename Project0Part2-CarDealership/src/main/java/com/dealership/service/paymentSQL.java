package com.dealership.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dealership.data.ConnectionUtility;
import com.dealership.model.Car;
import com.dealership.model.Payment;

public class paymentSQL {
	
	public static List<Payment> readSQLPayments() {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "select * from payments";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Payment> paymentList = new ArrayList<>();
			while(rs.next()) {
				paymentList.add(new Payment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			}
			ps.close();
			conn.close();
			return paymentList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void viewAllPayments() {
		List<Payment> paymentList = readSQLPayments();
		for(int i=0; i<paymentList.size(); i++) {
			System.out.println(paymentList.get(i));
		}
	}
	
	public static void addPayment(String newCarOwner, int offCarId, int offCarPrice) {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "insert into payments (payor, car_id, balance, scheduled_payment) values (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newCarOwner);
			ps.setInt(2, offCarId);
			ps.setInt(3, offCarPrice);
			ps.setInt(4, offCarPrice/72);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public static void viewCustomerCars() {
		List<Car> ownedCars = carLotSQL.readSQLCars();
		for(int i=0; i<ownedCars.size(); i++) {
			if(loginSQL.loggedIn.equals(ownedCars.get(i).getOwner())) {
				Car info = ownedCars.get(i);
				System.out.println(info);
			}
		}
	}
	
	public static void viewMonthlyPayments() {
		List<Payment> paymentList = readSQLPayments();
		for(int i=0; i<paymentList.size(); i++) {
			if(loginSQL.loggedIn.equals(paymentList.get(i).getPayor())) {
				System.out.println("Here are your owned cars: ");
				viewCustomerCars();
				
				System.out.println("Payment due for: ");
				int carPayId = paymentList.get(i).getCar_id();
				int carPayAmt = paymentList.get(i).getScheduled_payment();
				int carBalance = paymentList.get(i).getBalance();
				System.out.println("Car ID: "+carPayId);
				System.out.println("Current balance = $"+carBalance);
				System.out.println("Monthly amount due = $"+carPayAmt);
			}
		}
	}
	
}
