package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
	void testRequestAmount() {
		r.setAmount(-500.0);
		
		boolean test = es.request(r);
		
		assertFalse(test);
	}

	/*
	@Test
	void testRequest() {
		
	}
	*/
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






