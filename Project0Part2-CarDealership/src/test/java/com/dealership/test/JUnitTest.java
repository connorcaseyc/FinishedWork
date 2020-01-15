package com.dealership.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dealership.model.Car;
import com.dealership.model.Offer;
import com.dealership.service.carLotSQL;
import com.dealership.service.offerSQL;

public class JUnitTest {
	
	@Test
	public void testCarLotImport() {
		List<Car> carLot = carLotSQL.readSQLCars();
		int lotSize = carLot.size();
		int x = 0;
		assertTrue(x < lotSize);
	}
	
	@Test
	public void testPullOffer() {
		List<Offer> offerList = offerSQL.readSQLOffers();
		int test = offerList.size();
		offerList.add(new Offer(1, "testy", 2, 3));
		int test2 = offerList.size();
		assertTrue(test2 > test);
	}
	
	@Test
	public void testOfferCarId() {
		List<Offer> offerList = offerSQL.readSQLOffers();
		Offer test = offerList.get(1);
		int testCarId = test.getCarId();
		assertTrue(testCarId>0);
	}
	
}
