package com.revature;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repos.EmployeeDAOImple;
import com.revature.repos.LoginDAOImple;
import com.revature.repos.ManagerDAOImple;
import com.revature.utils.ConnectionUtil;

public class driver {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private static ManagerDAOImple mDao = new ManagerDAOImple();
	private static LoginDAOImple lDao = new LoginDAOImple();
	private static EmployeeDAOImple eDao = new EmployeeDAOImple();
	
	Connection conn = cu.getConnection();
	
	public static void main(String[] args) {
		
		/* User Test WORKS
		User u = lDao.findUser("CoolGuy", "Monster");
		System.out.println(u);
		*/
		
		/* EmployeeDao test WORKS
		Reimbursement reimb = new Reimbursement(8, 100, Date.valueOf("2020-06-19"), "driver", 1, 1, 1);
		eDao.addRequest(reimb);
		List<Reimbursement> list = eDao.viewTickets(1);
		
		for(Reimbursement r: list) {
			System.out.println(r);
		}
		*/
		
		//Manager Dao Test ALL THREE WORK
		/*
		List<Reimbursement> list = mDao.viewAllTickets();
		
		//mDao.rejectTicket(2);
		//mDao.acceptTicket(1);
		
		for(Reimbursement r: list) {
			System.out.println(r);
		}
		*/

		
		
		
		
		
	}
}
