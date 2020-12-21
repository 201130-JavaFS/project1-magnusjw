package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.IdDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ManagerService;

public class ManagerController {
	
	private static final Logger log = LogManager.getLogger(ManagerController.class);
	private ObjectMapper om = new ObjectMapper();
	private ManagerService ms = new ManagerService();
	
	public void approve(HttpServletRequest req, HttpServletResponse res) throws NumberFormatException, IOException {
		HttpSession ses = req.getSession(false);
		User user = (User)ses.getAttribute("user");
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		IdDTO idDTO = om.readValue(body, IdDTO.class);

		String result = ms.accept(idDTO.reimbId, user.getId());
		
		if(result == "success") {
			res.setStatus(200);
			
		} else if(result == "invalidReimbId") {
			res.setStatus(401);

		} else if(result == "pending"){
			res.setStatus(406);
			
		} else if(result == "notFound"){
			res.setStatus(404);
			
		} else {
			res.setStatus(400);
		}
	}
	
	public void reject(HttpServletRequest req, HttpServletResponse res) throws NumberFormatException, IOException {
		HttpSession ses = req.getSession(false);
		User user = (User)ses.getAttribute("user");
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		IdDTO idDTO = om.readValue(body, IdDTO.class);

		String result = ms.reject(idDTO.reimbId, user.getId());
		
		if(result == "success") {
			res.setStatus(200);
			
		} else if(result == "invalidReimbId") {
			res.setStatus(401);

		} else if(result == "pending"){
			res.setStatus(406);
			
		} else if(result == "notFound"){
			res.setStatus(404);
			
		} else {
			res.setStatus(400);
		}
	}
	
	public void viewAll(HttpServletResponse res) throws IOException {
		
		List<Reimbursement> list = ms.viewAll();
		String json = om.writeValueAsString(list);
		res.getWriter().print(json);
		res.setStatus(200);
		
		
	}
	
	public void viewPending(HttpServletResponse res) throws IOException {
		
		List<Reimbursement> list = ms.viewPending();;
		String json = om.writeValueAsString(list);
		res.getWriter().print(json);
		res.setStatus(200);
		
	}

}
