package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.EmployeeService;

public class EmployeeController {
	
	private static final Logger log = LogManager.getLogger(EmployeeController.class);
	private ObjectMapper om = new ObjectMapper();
	private EmployeeService es = new EmployeeService();
	
	public void viewTickets(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession ses = req.getSession(false);
		User user = (User)ses.getAttribute("user");
		
		List<Reimbursement> list = es.viewTickets(user.getId());;
		String json = om.writeValueAsString(list);
		res.getWriter().print(json);
		res.setStatus(200);
		
	}
	
	
	public void request(HttpServletRequest req, HttpServletResponse res) {
		HttpSession ses = req.getSession(false);
		User user = (User)ses.getAttribute("user");
		
		
		res.setStatus(200);
	}
}