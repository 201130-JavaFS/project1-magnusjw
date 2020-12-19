package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.repos.EmployeeDAOImple;
import com.revature.repos.ManagerDAOImple;

public class ManagerService {
	
	private ManagerDAOImple mDao = new ManagerDAOImple();
	private static final Logger log = LogManager.getLogger(EmployeeDAOImple.class);
	
	
}
