package com.dealership.model;

import java.io.Serializable;

public class Offer implements Serializable {
	
	private static final long serialVersionUID = -390186887744485040L;
	
	private int offerId;
	private String offerUser;
	private int carId;
	private int offerAmount;
	
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public String getOfferUser() {
		return offerUser;
	}
	public void setOfferUser(String offerUser) {
		this.offerUser = offerUser;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(int offerAmount) {
		this.offerAmount = offerAmount;
	}
	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerUser=" + offerUser + ", carId=" + carId + ", offerAmount="
				+ offerAmount + "]";
	}
	
	public Offer(int offerId, String offerUser, int carId, int offerAmount) {
		super();
		this.offerId = offerId;
		this.offerUser = offerUser;
		this.carId = carId;
		this.offerAmount = offerAmount;
	}
	public Offer() {}
	
	public Offer(Offer o) {
		this.offerId = o.getOfferId();
		this.offerUser = o.getOfferUser();
		this.carId = o.getCarId();
		this.offerAmount = o.getOfferAmount();
	}
	
}
