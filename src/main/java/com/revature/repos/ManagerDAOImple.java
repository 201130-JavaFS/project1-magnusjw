package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ManagerDAOImple{
	
	private static final Logger log = LogManager.getLogger(ManagerDAOImple.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public void acceptTicket(Reimbursement reimb) {
		
		Connection conn = cu.getConnection();
		log.info("Accepting ticket #" + reimb.getId());
		
		try {
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbId\" = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb.getId());
			
			ResultSet rs = ps.executeQuery();
			
			int status = 0; //Should never be 0
			
			if(rs.next()) {
				status = rs.getInt("reimbStatusId");
				if(status == 1) {// If pending
					status = 2; // Change to accepted
				}
			}
			
			String sql2 = "Update \"Ers_Reimbursements\" set \"reimbStatusId\" = ? and \"reimbResolved\" = ? and \"reimbResolver\" = ? where \"reimbId\" = ?;";
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, status);
			ps2.setInt(2, reimbId);
			ps2.setInt(2, reimbId);
			ps2.setInt(2, reimb.getId());
			
			ps2.executeUpdate();
			
			
		} catch(SQLException e) {
			System.out.println("ManagerDAO SQL error");
		}
	}
	
	public void rejectTicket(Reimbursement reimb) {
		
		Connection conn = cu.getConnection();
		log.info("Rejecting ticket #" + reimb.getId());
		
		try {
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbId\" = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb.getId());
			
			ResultSet rs = ps.executeQuery();
			
			int status = 0; //Should never be 0
			
			if(rs.next()) {
				status = rs.getInt("reimbStatusId");
				if(status == 1) {// If pending
					status = 3; // Change to rejected
				}
			}
			
			String sql2 = "Update \"Ers_Reimbursements\" set \"reimbStatusId\" = ? where \"reimbId\" = ?;";
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, status);
			ps2.setInt(2, reimb.getId());
			
			ps2.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("ManagerDAO SQL error");
		}
	}
	
	public List<Reimbursement> viewAll() {
		
		Connection conn = cu.getConnection();
		log.info("Viewing All Employee's Tickets");
		
		try {
			
			String sql = "select * from \"Ers_Reimbursements\";";
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			List<Reimbursement> list = new ArrayList<Reimbursement>();

			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt("reimbId"));
				r.setAmount(rs.getDouble("reimbAmount"));
				r.setSubmitted(rs.getDate("reimbSubmitted"));
				r.setResolved(rs.getDate("reimbResolved"));
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
			
			String sql = "select * from \"Ers_Reimbursements\" where \"reimbStatusId\" = 1;"; //Pending Tickets
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			List<Reimbursement> list = new ArrayList<Reimbursement>();

			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt("reimbId"));
				r.setAmount(rs.getDouble("reimbAmount"));
				r.setSubmitted(rs.getDate("reimbSubmitted"));
				r.setResolved(rs.getDate("reimbResolved"));
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