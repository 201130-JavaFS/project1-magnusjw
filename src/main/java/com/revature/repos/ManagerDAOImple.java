package com.revature.repos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtility;

public class ManagerDAOImple implements ManagerDAO{
	
	public void update(Reimbursement reimb) {
		Session ses = HibernateUtility.getSession();
		
		ses.merge(reimb);
	}
	/*
	 * Approve/Reject
	public void reject(Reimbursement reimb) {
		Session ses = HibernateUtility.getSession();
	}
	*/
	
	public List<Reimbursement> viewAll() {
		Session ses = HibernateUtility.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM ERS_REIMBURSEMENT").list();
		
		return list;
	}
}