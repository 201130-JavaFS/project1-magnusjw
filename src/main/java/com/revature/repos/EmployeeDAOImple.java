package com.revature.repos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtility;

public class EmployeeDAOImple implements EmployeeDAO{

	@Override
	public void addRequest(Reimbursement reimb) {
		Session ses = HibernateUtility.getSession();
		
		ses.save(reimb);
	}
	
	@Override
	public List<Reimbursement> viewTickets(int id) {
		Session ses = HibernateUtility.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHER = " + id).list();
		
		return list;
	}
}