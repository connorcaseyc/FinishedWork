package com.dealership.model;

public class Payment {
	
	private int payment_id;
	private String payor;
	private int car_id;
	private int balance;
	private int scheduled_payment;
	
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayor() {
		return payor;
	}
	public void setPayor(String payor) {
		this.payor = payor;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getScheduled_payment() {
		return scheduled_payment;
	}
	public void setScheduled_payment(int scheduled_payment) {
		this.scheduled_payment = scheduled_payment;
	}
	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", payor=" + payor + ", car_id=" + car_id + ", balance=" + balance
				+ ", scheduled_payment=" + scheduled_payment + "]";
	}
	
	public Payment(int payment_id, String payor, int car_id, int balance, int scheduled_payment) {
		super();
		this.payment_id = payment_id;
		this.payor = payor;
		this.car_id = car_id;
		this.balance = balance;
		this.scheduled_payment = scheduled_payment;
	}
	public Payment() {}
	
	public Payment(Payment p) {
		this.payment_id = p.getPayment_id();
		this.payor = p.getPayor();
		this.car_id = p.getCar_id();
		this.balance = p.getBalance();
		this.scheduled_payment = p.getScheduled_payment();
	}
	
	
	
	
	
}
