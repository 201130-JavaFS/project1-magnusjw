package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.repos.LoginDAOImple;

public class LoginService {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);
	private LoginDAOImple lDao = new LoginDAOImple();
	
	public User login(String username, String password) {
		
		log.info("Login Method");
		
		User user = lDao.findUser(username, password);
		
		if(user == null) {
			log.info("User Not Found");
			return null;
		}
		log.info("User #" + user.getId() + " Found");
		return user;
	}

}
