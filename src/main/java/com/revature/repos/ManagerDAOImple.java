package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ManagerDAOImple{
	
	private static final Logger log = LogManager.getLogger(ManagerDAOImple.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
	public String acceptTicket(int reimbId, int userId) {
		
		Connection conn = cu.getConnection();
		log.info("Accepting ticket #" + reimbId);
		
		try {
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbId\" = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int status = rs.getInt("reimbStatusId");
				
				if(status != 1) {
					log.info("Ticket is not pending");
					return "pending";
				}
			} else {
				log.info("Ticket not found, bad ticket id input");
				return "notFound";
			}
			
			String sql2 = "Update \"Ers_Reimbursements\" set \"reimbResolved\" = ?, \"reimbResolver\" = ?, \"reimbStatusId\" = 2  where \"reimbId\" = ?;";
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps2.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			ps2.setInt(2, userId);
			ps2.setInt(3, reimbId);
			
			ps2.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public String rejectTicket(int reimbId, int userId) {
		
		Connection conn = cu.getConnection();
		log.info("Rejecting ticket #" + reimbId);
		
		try {
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbId\" = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int status = rs.getInt("reimbStatusId");
				
				if(status != 1) {
					log.info("Ticket is not pending");
					return "pending";
				}
			} else {
				log.info("Ticket not found, bad ticket id input");
				return "notFound";
			}

			String sql2 = "Update \"Ers_Reimbursements\" set \"reimbResolved\" = ?, \"reimbResolver\" = ?, \"reimbStatusId\" = 3  where \"reimbId\" = ?;";
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps2.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			ps2.setInt(2, userId);
			ps2.setInt(3, reimbId);
			
			ps2.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public List<Reimbursement> viewAll() {
		
		Connection conn = cu.getConnection();
		log.info("Viewing All Employee's Tickets");
		
		try {
			
			String sql = "select * from \"Ers_Reimbursements\" order by \"reimbId\";";
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
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
			System.out.println("ManagerDAO SQL error");
		}
		return null; // Empty site huh
	}
	
	public List<Reimbursement> viewPending() {
		
		Connection conn = cu.getConnection();
		log.info("Viewing Pending Employee's Tickets");
		
		try {
			
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbStatusId\" = 1 order by \"reimbId\";"; //Pending Tickets
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
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
			System.out.println("ManagerDAO SQL error");
		}
		return null; // Empty site huh
	}
}