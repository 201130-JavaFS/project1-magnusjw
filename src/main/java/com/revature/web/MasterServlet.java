package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.ManagerController;
import com.revature.models.LoginDTO;
import com.revature.repos.EmployeeDAOImple;

public class MasterServlet extends HttpServlet{
	
	private static final Logger log = LogManager.getLogger(MasterServlet.class);
	private LoginController lc = new LoginController();
	private EmployeeController ec = new EmployeeController();
	private ManagerController mc = new ManagerController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//Employee - View past tickets
		//Manager  - View pending tickets
		//Manager  - View all tickets
		
		log.info("Master Servlet doGet Method");
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/project-1/", "");
		// By default tomcat will send back a successful status code 
		// servlet method.
		// Because all requests will his this method, we are defaulting
		// will override for success requests.
		
		switch (URI) {
			case "view":
				if(req.getSession(false)!=null) {

					ec.viewTickets(req, res);
					
				} else {
					res.setStatus(403);
				}
				break;
			case "viewPending":
				if(req.getSession(false)!=null) {
					
					mc.viewPending(res);
					
				} else {
					res.setStatus(403);
				}
				break;
			case "viewAll":
				if(req.getSession(false)!=null) {
					
					mc.viewAll(res);
					
				} else {
					res.setStatus(403);
				}
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ //Login
		//User 	   - Login
		//Employee - Create Request
		
		log.info("Master Servlet doPost Method");
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
			case "login":
				
				lc.login(req, res);
				
				if(res.getStatus() == 200) {
					log.info("200 Status code from Login");
				} else {
					log.info("Non 200 status code returned back to Master Servlet");
				}
				break;
				
			case "request":
				
				if(req.getSession(false)!=null) {
					ec.request(req, res);
					
				} else {
					res.setStatus(403);
				}
				break;
		}	
	}
	/*
	 * 	pw.print("<h2>Welcome " + req.getParameter("userId")+"!</h2>");
		pw.print("<a href='logout'>Click here to log out.</a>");
	 */
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ 
		//Manager - Approve a pending ticket
		//Manager - Reject a pending ticket
		log.info("Master Servlet doPut Method");
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
			case "approve":
				if(req.getSession(false)!=null) {
					
					mc.approve(req, res);
					
					
				} else {
					res.setStatus(403);
				}
				break;
			case "reject":
				if(req.getSession(false)!=null) {
					
					mc.reject(req, res);
					
					
				} else {
					res.setStatus(403);
				}
				break;
		}
	}
	
	/*
	 * Get - Request Information from the server
	 * Post - Create new object, add new information
	 * Put - Update a complete resource
	 */
}