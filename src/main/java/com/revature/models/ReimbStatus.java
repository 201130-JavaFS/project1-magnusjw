package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ReimbStatus {
	
	private int id;
	private String status; // 1:Pending , 2:Approved , 3:Denied

}
