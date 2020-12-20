package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.repos.EmployeeDAOImple;

public class EmployeeService {
	
	private static final Logger log = LogManager.getLogger(EmployeeService.class);
	private EmployeeDAOImple eDao = new EmployeeDAOImple();
	
	
	public void request(Reimbursement reimb) {
		
		eDao.addRequest(reimb);
	}
	
	public List<Reimbursement> viewTickets(int id) {
		
		List<Reimbursement> list = eDao.viewTickets(id);
		
		return list;
	}
}