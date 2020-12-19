package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.ManagerController;

public class MasterServlet extends HttpServlet{
	
	private LoginController lc = new LoginController();
	private EmployeeController ec = new EmployeeController();
	private ManagerController mc = new ManagerController();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code 
		// servlet method.
		// Because all requests will his this method, we are defaulting
		// will override for success requests.
		res.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch (URI) {
		case "avengers":
			if(req.getSession(false)!=null) {
				//ac.getAllAvengers(res); Any basic action of our system
			} else {
				res.setStatus(403);
			}
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ //Login
		
		res.setContentType("application/json");
		res.setStatus(404);
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
		case "login":
			
			lc.login(req, res);
			RequestDispatcher rd = null;
			PrintWriter pw = res.getWriter();
			
			if(res.getStatus() == 200) {
				rd = req.getRequestDispatcher("employee.html");
				rd.forward(req, res);
			} else {
				rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);
				pw.print("<span style='color:red; text-align:center;'>Invalid Username and/or Password</span>");
			}
			
			break;
		// case of second post method
		}
	}
	
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
