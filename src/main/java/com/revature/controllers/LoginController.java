package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.LoginService;
import com.revature.models.LoginDTO;

public class LoginController {
	
	private ObjectMapper om = new ObjectMapper();
	private LoginService ls = new LoginService();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("POST")) {
			
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
			
			LoginDTO loginDTO = om.readValue(body, LoginDTO.class);
			
			if(ls.login(loginDTO.username, loginDTO.password)) {
				HttpSession ses = req.getSession();
				
				ses.setAttribute("user", loginDTO);//probably give it the login object if I had one
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
