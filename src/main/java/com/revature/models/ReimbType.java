package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ERS_REIMBURSEMENT_TYPE")
public class ReimbType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REIMB_TYPE_ID")
	private int id;
	
	@Column(name="REIMB_TYPE")
	private String type; // 1:Lodging , 2:Travel , 3:Food , 4:Other

}