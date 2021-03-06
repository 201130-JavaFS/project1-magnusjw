package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.LoginService;
import com.revature.models.LoginDTO;
import com.revature.models.User;

public class LoginController {
	
	private static final Logger log = LogManager.getLogger(LoginController.class);
	private ObjectMapper om = new ObjectMapper();
	private LoginService ls = new LoginService();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("POST")) {
			
			log.info("Login in LoginController");
			
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
			
			LoginDTO loginDTO = om.readValue(body, LoginDTO.class);
			
			User user = ls.login(loginDTO.username, loginDTO.password);
			
			if(user.getRoleId() != 0) {
				log.info("User Found");
				HttpSession ses = req.getSession();
				
				ses.setAttribute("user", user);
				
				ses.setAttribute("loggedin", true);
				
				res.setStatus(200);
				res.getWriter().print(user.getRoleId());
				
				log.info("User Role id is " + user.getRoleId());
				
			} else {
				log.info("User Not Found");
				HttpSession ses = req.getSession(false);
				if(ses != null) {
					ses.invalidate();
				}
				res.setStatus(404); //This is returned when user not found
			}
		} else {
			//Not a post request
			// A non-post request shouldnt be sent here in the first place so remove this once figured out
		}
	}
}

/*
else if(req.getMethod().equals("GET")) {
	//This shows logging in with Query Params solely for the
	//example of using said parameters. Do not do this!
	if(req.getParameterMap().containsKey("username") && req.getParameterMap().containsKey("password")) {
		if(ls.login(req.getParameter("username"), req.getParameter("password"))) {
			HttpSession ses = req.getSession();
			
			ses.setAttribute("loggedin", true);
			
			res.setStatus(200);
			res.getWriter().print("Login Successful");
		} else {
			HttpSession ses = req.getSession(false);
			if(ses != null) {
				ses.invalidate();
			}
			res.setStatus(401);
		}
	}
}
*/
