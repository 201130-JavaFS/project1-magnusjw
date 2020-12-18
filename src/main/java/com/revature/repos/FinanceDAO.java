package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface FinanceDAO {
	
	public void update(Reimbursement reimb);
	public List<Reimbursement> viewAll();

}
