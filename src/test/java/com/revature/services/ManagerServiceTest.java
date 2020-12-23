package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ManagerServiceTest {
	
	private static ManagerService ms;

	@BeforeAll
	public static void setUp() {
		ms = new ManagerService();
	}
	
	@Test
	void testInvalidReimbAcc() {
		String test = ms.accept(0, 1);
		
		assertEquals(test, "invalidReimbId");
	}
	
	@Test
	void testInvalidReimbRej() {
		String test = ms.reject(-20, 1);
		
		assertEquals(test, "invalidReimbId");
	}
	
	@Test
	void testPendingReimbAcc() {
		String test = ms.accept(24, 1); //Existing ticket that is not pending
		
		assertEquals(test, "pending");
	}
	
	@Test
	void testPendingReimbRej() {
		String test = ms.reject(24, 1); //Existing ticket that is not pending
		
		assertEquals(test, "pending");
	}
	
	@Test
	void testIllegalReimbAcc() {
		String test = ms.accept(500, 1);
		
		assertEquals(test, "notFound");
	}
	
	@Test
	void testIllegalReimbRej() {
		String test = ms.reject(500, 1);
		
		assertEquals(test, "notFound");
	}
}