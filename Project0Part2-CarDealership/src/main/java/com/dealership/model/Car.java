package com.dealership.model;

public class Car {
	
	private int id;
	private String make;
	private String color;
	private String owner;
	private int price;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", color=" + color + ", owner=" + owner + ", price=" + price + "]\n";
	}
	
	public Car(int id, String make, String color, String owner, int price) {
		super();
		this.id = id;
		this.make = make;
		this.color = color;
		this.owner = owner;
		this.price = price;
	}
	public Car() {}

	public Car(Car c) {
		this.id = c.getId();
		this.make = c.getMake();
		this.color = c.getColor();
		this.owner = c.getOwner();
		this.price = c.getPrice();
	}
	
}
