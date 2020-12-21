package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.repos.EmployeeDAOImple;

public class EmployeeService {
	
	private static final Logger log = LogManager.getLogger(EmployeeService.class);
	private EmployeeDAOImple eDao = new EmployeeDAOImple();
	
	
	public String request(Reimbursement reimb) {
		
		log.info("Request Method");
		
		if(reimb.getAmount() < 0) {
			return "negative";
		}
		if(reimb.getAmount() == 0) {
			return "zero";
		}
		if(reimb.getTypeId() < 1 || reimb.getTypeId() > 4) {
			return "badType";
		}
		
		eDao.addRequest(reimb);
		return "success";
	}
	
	public List<Reimbursement> viewTickets(int id) {
		
		log.info("View Tickets Method");
		
		List<Reimbursement> list = eDao.viewTickets(id);
		
		return list;
	}
}