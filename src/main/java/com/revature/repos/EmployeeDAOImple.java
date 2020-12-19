package com.revature.repos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtility;

public class EmployeeDAOImple implements EmployeeDAO{
	
	private static final Logger log = LogManager.getLogger(EmployeeDAOImple.class);

	@Override
	public void addRequest(Reimbursement reimb) {
		Session ses = HibernateUtility.getSession();
		
		log.info("I'm adding request " + reimb.getId() + " using EmployeeDAO");
		
		ses.save(reimb);
	}
	
	@Override
	public List<Reimbursement> viewTickets(int id) {
		Session ses = HibernateUtility.getSession();
		
		log.info("I'm viewing my requests using my id: " + id);
		
		List<Reimbursement> list = ses.createQuery("FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHER = " + id).list();
		
		return list;
	}
}