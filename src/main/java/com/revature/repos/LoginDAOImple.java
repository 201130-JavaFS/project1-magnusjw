package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class LoginDAOImple {
	
	private static final Logger log = LogManager.getLogger(LoginDAOImple.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	
	public User findUser(String username, String password) {
		
		log.info("findUser in LoginDAOImple");
		
		Connection conn = cu.getConnection();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("select * from \"Ers_Users\" where \"username\" = ? and \"password\" = ?;");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUsername(username);
				u.setPassword(password);
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setEmail(rs.getString("email"));
				
				return u;
			}
		} catch(SQLException e) {
			System.out.println("LoginDAO SQL error");
		}
		return null; //If user is not found
	}
}