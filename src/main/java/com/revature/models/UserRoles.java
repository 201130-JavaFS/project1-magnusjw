package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ERS_USER_ROLES")
public class UserRoles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ERS_USER_ROLE_ID")
	private int id;
	
	@Column(name="USER_ROLE")
	private String role; // 1:Employee , 2:Finance Manager

}