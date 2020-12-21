package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.User;

class LoginServiceTest {

	private static LoginService ls;
	private static User u;
	
	@BeforeAll
	public static void setUp() {
		ls = new LoginService();
		u = new User();
	}
	
	@Test
	void testNullLogin() {
		u.setUsername("nonExistantUser");
		u.setPassword("badPassword");
		
		User result = ls.login(u.getUsername(), u.getPassword());
		
		assertEquals(result, null);
	}

}
