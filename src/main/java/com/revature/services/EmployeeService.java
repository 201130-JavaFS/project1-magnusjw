package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.repos.EmployeeDAOImple;

public class EmployeeService {
	
	private EmployeeDAOImple eDao = new EmployeeDAOImple();
	private static final Logger log = LogManager.getLogger(EmployeeDAOImple.class);

}
