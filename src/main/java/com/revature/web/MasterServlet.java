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
import com.revature.repos.EmployeeDAOImple;

public class MasterServlet extends HttpServlet{
	
	private static final Logger log = LogManager.getLogger(MasterServlet.class);
	private LoginController lc = new LoginController();
	private EmployeeController ec = new EmployeeController();
	private ManagerController mc = new ManagerController();
	
	private int currUserId = 0;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		//Employee - View past tickets
		//Manager  - View pending tickets
		//Manager  - View all tickets
		
		log.info("Master Servlet doGet Method");
		
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code 
		// servlet method.
		// Because all requests will his this method, we are defaulting
		// will override for success requests.
		 
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		System.out.println("Session id is " + currUserId);
		
		switch (URI) {
			case "view":
				if(req.getSession(false)!=null) {
					
					currUserId = Integer.parseInt(req.getRequestedSessionId());
					System.out.println("Session id is " + currUserId);
					ec.viewTickets();
					
				} else {
					res.setStatus(403);
				}
				break;
			case "viewall":
				if(req.getSession(false)!=null) {
					
					currUserId = Integer.parseInt(req.getRequestedSessionId());
					System.out.println("Session id is " + currUserId);
					mc.viewAll();
					
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
				RequestDispatcher rd = null;
				PrintWriter pw = res.getWriter();
				System.out.println("test");
				if(res.getStatus() == 200) {
					System.out.println("test1");
					/*
					rd = req.getRequestDispatcher("manager.html");
					rd.include(req, res);
					*/
				} else {
					
					System.out.println("Non 200 status code returned back to Master Servlet");
				}
			
				break;
			case "request":
				
				if(req.getSession(false)!=null) {
					ec.request();
					
				} else {
					res.setStatus(403);
				}
				break;
		// case of second post method
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ 
		//Manager - Approve or Reject a pending ticket
		log.info("Master Servlet doPut Method");
		
		res.setContentType("application/json");
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
			case "approve":
				if(req.getSession(false)!=null) {
					
					currUserId = Integer.parseInt(req.getRequestedSessionId());
					System.out.println("Session id is " + currUserId);
					mc.approve();
					
					
				} else {
					res.setStatus(403);
				}
				break;
			case "reject":
				if(req.getSession(false)!=null) {
					
					currUserId = Integer.parseInt(req.getRequestedSessionId());
					System.out.println("Session id is " + currUserId);
					mc.reject();
					
					
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
	
	//res.sendRedirect("http://www.google.com");

	/* Success Servlet
	//This is bad practice but it is possible and so I am showing it.	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.print("<h2>Welcome " + req.getParameter("userId")+"!</h2>");
		pw.print("<a href='logout'>Click here to log out.</a>");
	}
	*/
	
}
