package com.revature.services;

import com.revature.models.User;
import com.revature.repos.LoginDAOImple;

public class LoginService {

	//check if the entered credentials are within the database
	
	private LoginDAOImple lDao = new LoginDAOImple();
	
	public boolean login(String username, String password) {
		
		System.out.println("test1");
		
		User user = lDao.findByUsername(username);
		
		System.out.println("test2");
		
		//How can I get this user information back to somewhere important
		
		if(!user.equals(null)) {//User not found in first place
			return false;
		} else if(!user.getPassword().equals(password)) { //Password is incorrect for this username
			return false;
		}
		
		System.out.println("test7");
		
		return true;
	}

}
