package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.repos.ManagerDAOImple;

public class ManagerService {
	
	private static final Logger log = LogManager.getLogger(ManagerService.class);
	private ManagerDAOImple mDao = new ManagerDAOImple();
	
	public String accept(int reimbId, int userId) {
		
		log.info("Accept Method");
		
		if(reimbId < 0 || reimbId == 0) {
			return "invalidReimbId";
		}
		
		String result = mDao.acceptTicket(reimbId, userId);
		
		if(result == "pending") {
			return "pending";
		}
		if(result == "notFound") {
			return "notFound";
		}
		
		return "success";
		
	}
	
	public String reject(int reimbId, int userId) {
		
		log.info("Reject Method");
		
		if(reimbId < 0 || reimbId == 0) {
			return "invalidReimbId";
		}
		
		String result = mDao.rejectTicket(reimbId, userId);
		
		if(result == "pending") {
			return "pending";
		}
		if(result == "notFound") {
			return "notFound";
		}
		
		return "success";
		
	}
	
	public List<Reimbursement> viewAll() {
		
		log.info("View All Tickets Method");
		
		return mDao.viewAll();
	}
	
	public List<Reimbursement> viewPending(){
		
		log.info("View Pending Tickets Method");
		
		return mDao.viewPending();
	}
	
}
