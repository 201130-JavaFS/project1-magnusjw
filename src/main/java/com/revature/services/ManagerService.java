package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.repos.EmployeeDAOImple;
import com.revature.repos.ManagerDAOImple;

public class ManagerService {
	
	private static final Logger log = LogManager.getLogger(ManagerService.class);
	private ManagerDAOImple mDao = new ManagerDAOImple();
	
	public boolean accept(int reimbId, int userId) {
		
		if(mDao.acceptTicket(reimbId, userId)) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean reject(int reimbId, int userId) {
		
		if(mDao.rejectTicket(reimbId, userId)) {
			return true;
		}
		
		return false;
		
	}
	
	public List<Reimbursement> viewAll() {
		
		return mDao.viewAll();
	}
	
	public List<Reimbursement> viewPending(){
		
		return mDao.viewPending();
	}
	
}
