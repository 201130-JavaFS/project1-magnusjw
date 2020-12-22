package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursement;

class EmployeeServiceTest {

	
	private static EmployeeService es;
	private static Reimbursement r;
	
	@BeforeAll
	public static void setUp() {
		es = new EmployeeService();
		r = new Reimbursement();
		
	}
	
	@Test
	void testNegativeAmount() {
		r.setAmount(-500.0);
		
		String test = es.request(r);
		
		assertEquals(test, "negative");
	}
	
	@Test
	void testZeroAmount() {
		r.setAmount(0);
		
		String test = es.request(r);
		
		assertEquals(test, "zero");
	}
	
	@Test
	void testBadType() {
		r.setTypeId(0);
		r.setAmount(10);
		
		String test = es.request(r);
		
		assertEquals(test, "badType");
	}
}


/*
 * assertTrue single params
 * assertFalse
 * 
 * assertEquals (1, id) 1st any var, compared to 2nd
 * 
 * assertNotEquals(0, list.size)
 * 
 * assertNull(if null) 1param
 * 
 */






