package com.revature.services;

public class LoginService {

	//check if the entered credentials are within the database
	
	
	public boolean login(String username, String password) {
		if(username.equals("agent") && password.equals("cellist")) {
			return true;
		}
		return false;
	}

}
