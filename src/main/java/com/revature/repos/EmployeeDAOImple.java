package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;;

public class EmployeeDAOImple{
	
	private static final Logger log = LogManager.getLogger(EmployeeDAOImple.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

	public boolean addRequest(Reimbursement reimb) {
		
		Connection conn = cu.getConnection();
		log.info("I'm adding request " + reimb.getId() + " using EmployeeDAO");
		
		try {
			String sql = "insert into \"Ers_Reimbursements\" (\"reimbAmount\", \"reimbSubmitted\", \"reimbDescription\", \"reimbAuthor\", \"reimbStatusId\", \"reimbTypeId\") values (?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, reimb.getAmount());
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(3, reimb.getDescription());
			ps.setInt(4, reimb.getAuthorId());
			ps.setInt(5,reimb.getStatusId());
			ps.setInt(6, reimb.getTypeId());
			
			ps.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			System.out.println("EmployeeDAO SQL error");
		}
		
		return false;
	}
	
	public List<Reimbursement> viewTickets(int id) {
	
		Connection conn = cu.getConnection();
		log.info("Viewing Employee " + id + "'s Tickets");
		
		try {
			
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbAuthor\" = ? order by \"reimbId\";";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			List<Reimbursement> list = new ArrayList<Reimbursement>();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt("reimbId"));
				r.setAmount(rs.getDouble("reimbAmount"));
				
				Timestamp submitted = rs.getTimestamp("reimbSubmitted");
				String strSubmitted = dateFormat.format(submitted);
				r.setSubmitted(strSubmitted);
				
				Timestamp resolved = rs.getTimestamp("reimbResolved");
				String strResolved;
				
				if(resolved == null) {
					strResolved = "";
				} else {
					strResolved = dateFormat.format(resolved);
				}

				r.setResolved(strResolved);
				r.setDescription(rs.getString("reimbDescription"));
				r.setAuthorId(rs.getInt("reimbAuthor"));
				r.setResolverId(rs.getInt("reimbResolver"));
				r.setStatusId(rs.getInt("reimbStatusId"));
				r.setTypeId(rs.getInt("reimbTypeId"));

				list.add(r);
			}
			
			return list;
			
		} catch(SQLException e) {
			System.out.println("EmployeeDAO SQL error");
		}
		return null; //breh the site is lookin empty
	}
}