package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface EmployeeDAO {
	
	public void addRequest(Reimbursement reimb);
	public List<Reimbursement> viewTickets(int id);

}