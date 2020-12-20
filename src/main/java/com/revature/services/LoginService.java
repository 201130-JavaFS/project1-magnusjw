package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.repos.LoginDAOImple;

public class LoginService {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);
	private LoginDAOImple lDao = new LoginDAOImple();
	
	public boolean login(String username, String password) {
		
		User user = lDao.findUser(username, password);
		
		if(user == null) {
			System.out.println("service false");
			return false;
		}
		System.out.println("service true");
		return true;
	}

}
