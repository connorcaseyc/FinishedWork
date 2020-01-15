package com.dealership.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dealership.data.ConnectionUtility;
import com.dealership.model.Car;

public class carLotSQL {
	
	public static List<Car> readSQLCars() {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "select * from cars";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Car> carList = new ArrayList<>();
			while(rs.next()) {
				carList.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			ps.close();
			conn.close();
			return carList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void viewCarLot() {
		List<Car> carList = readSQLCars();
		int length = carList.size();
		for(int i=0; i<length; i++) {
			if(carList.get(i).getOwner().equals("dealer")) {
				System.out.println(carList.get(i));
			}
		}
	}
	public static void viewCustomerOwnedCars() {
		List<Car> carList = readSQLCars();
		int length = carList.size();
		for(int i=0; i<length; i++) {
			if(!carList.get(i).getOwner().equals("dealer")) {				
				System.out.println(carList.get(i));
			}
		}
	}
	
	public static void addCarToList(Scanner scan) {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "insert into cars (make, color, owner, price) values (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("Enter the make of the car: ");
			ps.setString(1, scan.next());
			System.out.println("Enter the color of the car: ");
			ps.setString(2, scan.next());
			ps.setString(3, "dealer");
			System.out.println("Enter the price of the car: ");
			ps.setInt(4, scan.nextInt());
			ps.executeUpdate();
			ps.close();
			conn.close();
			System.out.println("Here is the updated lot: ");
			viewCarLot();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeCarOnList(Scanner scan) {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "delete from cars where car_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("Here is the current lot: ");
			viewCarLot();
			System.out.println("Enter the car ID number to remove: ");
			ps.setInt(1, scan.nextInt());
			ps.executeUpdate();
			ps.close();
			conn.close();
			System.out.println("Here is the updated car lot: ");
			viewCarLot();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void assignOwner(String newOwner, int onCarId) {
		try {
			Connection conn = ConnectionUtility.connect();
			String sql = "update cars set owner = ? where car_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newOwner);
			ps.setInt(2, onCarId);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
