package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ManagerDAO {
	
	public void update(Reimbursement reimb);
	public List<Reimbursement> viewAll();

}
